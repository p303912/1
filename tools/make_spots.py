import json, sys
def pts(g):
    t = g["type"]; c = g["coordinates"]
    if t == "Point": return [c]
    if t == "LineString": return c
    if t == "Polygon": return c[0]
    if t == "MultiPolygon": return [p for poly in c for p in poly[0]]
    return []
out, seen = [], set()
for f in sys.argv[1:]:
    for line in open(f, encoding="utf-8"):
        line = line.strip("\x1e \n")
        if not line: continue
        o = json.loads(line); p = o.get("properties", {}); g = o.get("geometry")
        if not g: continue
        name = (p.get("name:zh") or p.get("name") or "").strip().replace("|", " ").replace(";", " ")
        if p.get("tourism") == "viewpoint": k = "v"
        elif p.get("natural") == "peak": k = "m"
        elif p.get("natural") == "beach": k = "b"
        elif p.get("leisure") == "park": k = "p"
        else: continue
        if k != "v" and not name: continue
        q = pts(g)
        if not q: continue
        lon = (min(x[0] for x in q) + max(x[0] for x in q)) / 2
        lat = (min(x[1] for x in q) + max(x[1] for x in q)) / 2
        a, b = round(lat * 1e4), round(lon * 1e4)
        key = (k, name, a // 50, b // 50)
        if key in seen: continue
        seen.add(key); out.append(f"{a}|{b}|{k}|{name}")
sys.stderr.write(f"{len(out)} spots\n")
print("window.SPOTS=" + json.dumps(";".join(out), ensure_ascii=False) + ";")
