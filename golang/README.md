## HTTP2 server based on Golang net::http2

The code is borrowed from https://posener.github.io/http2/

# Run 

    PORT=18080 go run http2-server.go

# Load test it with:

    echo -e '{"method": "GET", "url": "https://localhost:18080/"}' | vegeta attack -format=json -http2 -rate=0 -max-workers=1000 -insecure -duration=20s | vegeta encode > /tmp/http2.json; and vegeta report -type=json /tmp/http2.json | jq .


# Result

```json
{
  "latencies": {
    "total": 20230553202909,
    "mean": 40401715,
    "50th": 13002924,
    "90th": 22761019,
    "95th": 27637338,
    "99th": 1024114251,
    "max": 15045178344,
    "min": 96759
  },
  "bytes_in": {
    "total": 5508085,
    "mean": 11
  },
  "bytes_out": {
    "total": 0,
    "mean": 0
  },
  "earliest": "2020-09-18T09:47:20.500010256+03:00",
  "latest": "2020-09-18T09:47:40.500427708+03:00",
  "end": "2020-09-18T09:47:46.559506414+03:00",
  "duration": 20000417452,
  "wait": 6059078706,
  "requests": 500735,
  "rate": 25036.227428839367,
  "throughput": 19215.06835604262,
  "success": 1,
  "status_codes": {
    "200": 500735
  },
  "errors": []
}

```