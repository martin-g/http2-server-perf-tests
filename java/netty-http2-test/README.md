## HTTP2 server based on netty.io

The code is borrowed from https://www.baeldung.com/netty-http2

    echo -e '{"method": "GET", "url": "https://localhost:18080/"}' | vegeta attack -format=json -http2 -rate=0 -max-workers=1000 -insecure -duration=20s | vegeta encode > /tmp/http2.json; and vegeta report -type=json /tmp/http2.json | jq .

gives

```json
{
  "latencies": {
    "total": 19368863911826,
    "mean": 32982988,
    "50th": 32485047,
    "90th": 45151634,
    "95th": 49293757,
    "99th": 78361529,
    "max": 196080822,
    "min": 65023
  },
  "bytes_in": {
    "total": 7046856,
    "mean": 12
  },
  "bytes_out": {
    "total": 0,
    "mean": 0
  },
  "earliest": "2020-09-17T16:04:38.943635404+03:00",
  "latest": "2020-09-17T16:04:58.943950977+03:00",
  "end": "2020-09-17T16:04:58.954451586+03:00",
  "duration": 20000315573,
  "wait": 10500609,
  "requests": 587238,
  "rate": 29361.436716166554,
  "throughput": 29346.02940025148,
  "success": 1,
  "status_codes": {
    "200": 587238
  },
  "errors": []
}

```