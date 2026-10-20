# 霞光 · 火烧云预报

一款免费、无广告、无需登录的安卓火烧云（朝霞 / 晚霞）预报 App。

## 下载安装
1. 打开本仓库右侧的 **Releases**，选最新版本。
2. 下载 `XiaguangForecast.apk`，在安卓手机上点开安装（如提示"未知来源"，允许即可）。
3. 首次打开会请求定位，用于预报你所在地；也可以直接搜索任意城市。

**大陆网络加速下载**（始终是最新版，任选一个）：
- https://ghfast.top/https://github.com/p20051230/1/releases/latest/download/XiaguangForecast.apk
- https://gh-proxy.com/https://github.com/p20051230/1/releases/latest/download/XiaguangForecast.apk

> 如果安装时提示签名冲突，先卸载旧版本再安装新版。

## 功能
- **全球任意地点**：城市搜索 + 一键定位，内置常用城市快捷按钮。
- **7 天日出 / 日落评分**：0–100 分，对应 不烧 / 微烧 / 小烧 / 中烧 / 大烧 / 超级大烧。
- **下一场提醒**：最近一次日出或日落的等级、原因、时间和倒计时。
- **详情**：本地低 / 中 / 高云量、光路云量、气溶胶、降水概率。

## 预测原理
- **五模型集合**：同时调用 ECMWF（欧洲）、GFS（美国）、ICON（德国）、CMA（中国）、JMA（日本）五个全球数值模型，分别打分后取中位数；12 小时内的预报对 ECMWF 和 ICON 加权。
- **光路检测**：除了本地云况，还检查太阳方向约 150 公里处的低 / 中云，判断阳光能否照亮云底。
- **评分要点**：高 / 中云是"画布"，低云挡光，光路要通透，气溶胶（AOD）越低颜色越艳，降雨概率高则扣分。

数据来源：[Open-Meteo](https://open-meteo.com)（免费公开气象接口）。

## 说明
- 火烧云受云层瞬时变化影响很大，任何预报都无法保证 100% 准确；当天和次日的结果最可靠。
- 本项目与 sunsetbot.top 无关，非其官方产品。

## 构建
推送到 `main` 分支后，GitHub Actions 会自动编译 APK 并发布到 Releases（见 `.github/workflows/xiaguang-apk.yml`）。
