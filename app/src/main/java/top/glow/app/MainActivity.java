package top.glow.app;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.webkit.*;
public class MainActivity extends Activity {
  private WebView web;
  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    if (Build.VERSION.SDK_INT >= 23) requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION"}, 1);
    getWindow().setStatusBarColor(0xFF0F0C29); getWindow().setNavigationBarColor(0xFF24243E);
    web = new WebView(this);
    WebSettings s = web.getSettings();
    s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setGeolocationEnabled(true);
    s.setAllowFileAccess(true); s.setAllowUniversalAccessFromFileURLs(true);
    web.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
        Uri u = r.getUrl();
        if ("file".equals(u.getScheme())) return false;
        try { startActivity(new Intent(Intent.ACTION_VIEW, u)); } catch (Exception e) {}
        return true;
      }
    });
    web.setWebChromeClient(new WebChromeClient() {
      @Override public void onGeolocationPermissionsShowPrompt(String o, GeolocationPermissions.Callback cb) { cb.invoke(o, true, false); }
    });
    setContentView(web);
    web.loadUrl("file:///android_asset/index.html");
  }
  @Override public void onRequestPermissionsResult(int code, String[] perms, int[] res) {
    for (int r : res) if (r == 0) { if (web != null) web.evaluateJavascript("locate(current)", null); break; }
  }
  @Override public void onBackPressed() { if (web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}

