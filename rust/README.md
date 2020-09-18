## HTTP2 server based on Rust Actix-web framework

The code is borrowed from https://actix.rs/docs/http2/

Run the server with: `cargo run`

    echo -e '{"method": "GET", "url": "https://localhost:18080/"}' | vegeta attack -format=json -http2 -rate=0 -max-workers=1000 -insecure -duration=20s | vegeta encode > /tmp/http2.json; and vegeta report -type=json /tmp/http2.json | jq .

gives

```json
{
  "latencies": {
    "total": 20035078165592,
    "mean": 309321736,
    "50th": 484999133,
    "90th": 702407587,
    "95th": 734381616,
    "99th": 1100500334,
    "max": 1420217245,
    "min": 446348
  },
  "bytes_in": {
    "total": 777252,
    "mean": 12
  },
  "bytes_out": {
    "total": 0,
    "mean": 0
  },
  "earliest": "2020-09-18T10:10:10.742275836+03:00",
  "latest": "2020-09-18T10:10:30.742292062+03:00",
  "end": "2020-09-18T10:10:30.939907218+03:00",
  "duration": 20000016226,
  "wait": 197615156,
  "requests": 64771,
  "rate": 3238.5473725665165,
  "throughput": 3206.8611796590912,
  "success": 1,
  "status_codes": {
    "200": 64771
  },
  "errors": []
}
```