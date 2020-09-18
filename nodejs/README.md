## HTTP2 server based on Node.js

The code is borrowed from https://nodejs.org/api/http2.html

# Run

    node http2-server.js


# Load test with Vegeta

    echo -e '{"method": "GET", "url": "https://localhost:18080/"}' | vegeta attack -format=json -http2 -rate=0 -max-workers=1000 -insecure -duration=20s | vegeta encode > /tmp/http2.json; and vegeta report -type=json /tmp/http2.json | jq .


Result:

```json
{
  "latencies": {
    "total": 19013447379604,
    "mean": 51585952,
    "50th": 48221732,
    "90th": 60301686,
    "95th": 65258222,
    "99th": 83370456,
    "max": 568439303,
    "min": 3918474
  },
  "bytes_in": {
    "total": 4422936,
    "mean": 12
  },
  "bytes_out": {
    "total": 0,
    "mean": 0
  },
  "earliest": "2020-09-17T16:13:18.55465095+03:00",
  "latest": "2020-09-17T16:13:38.558992078+03:00",
  "end": "2020-09-17T16:13:38.587795228+03:00",
  "duration": 20004341128,
  "wait": 28803150,
  "requests": 368578,
  "rate": 18424.900757371248,
  "throughput": 18398.409899376853,
  "success": 1,
  "status_codes": {
    "200": 368578
  },
  "errors": []
}
```