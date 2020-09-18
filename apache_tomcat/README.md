# Apache Tomcat HTTP2

# Config

To enable HTTP2 in Apache Tomcat one needs to add `UpgradeProtocol` element

    <UpgradeProtocol className="org.apache.coyote.http2.Http2Protocol"/>

to a `Connector`

    <Connector port="18080" 
        maxThreads="8" 
        protocol="org.apache.coyote.http11.Http11NioProtocol" 
        SSLEnabled="true" >
        
        <UpgradeProtocol 
            className="org.apache.coyote.http2.Http2Protocol"
            maxConcurrentStreams="1024" 
            maxConcurrentStreamExecution="1024"/>
        
        <SSLHostConfig>
            <Certificate 
                certificateKeyFile="/path/to/server.key"
                certificateFile="/path/to/server.crt"
                type="RSA" />
        </SSLHostConfig>
        
    </Connector>

# Run

    ./bin/startup.sh

# Load test

    echo -e '{"method": "GET", "url": "https://localhost:18080/examples/index.html"}' | vegeta attack -format=json -http2 -rate=0 -max-workers=1000 -insecure -duration=20s | vegeta encode > /tmp/http2.json; and vegeta report -type=json /tmp/http2.json | jq .

Result:

```json
{
  "latencies": {
    "total": 23932069640313,
    "mean": 3241071186,
    "50th": 4543734266,
    "90th": 5225776331,
    "95th": 5333538404,
    "99th": 5387444101,
    "max": 5463476221,
    "min": 26141128
  },
  "bytes_in": {
    "total": 5529786,
    "mean": 748.8875947995666
  },
  "bytes_out": {
    "total": 0,
    "mean": 0
  },
  "earliest": "2020-09-18T10:38:21.080708276+03:00",
  "latest": "2020-09-18T10:38:45.255777782+03:00",
  "end": "2020-09-18T10:38:45.397173879+03:00",
  "duration": 24175069506,
  "wait": 141396097,
  "requests": 7384,
  "rate": 305.4386254470693,
  "throughput": 201.96191667731983,
  "success": 0.6650866738894908,
  "status_codes": {
    "0": 2473,
    "200": 4911
  },
  "errors": [
    "Get \"https://localhost:8443/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=2387, ErrCode=PROTOCOL_ERROR, debug=\"Stream [429] has been closed for some time\"",
    "Get \"https://localhost:8443/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=2277, ErrCode=PROTOCOL_ERROR, debug=\"Stream [335] has been closed for some time\"",
    "Get \"https://localhost:8443/examples/index.html\": stream error: stream ID 545; STREAM_CLOSED",
    "Get \"https://localhost:8443/examples/index.html\": stream error: stream ID 547; STREAM_CLOSED",
    "Get \"https://localhost:8443/examples/index.html\": stream error: stream ID 551; STREAM_CLOSED",
    "Get \"https://localhost:8443/examples/index.html\": stream error: stream ID 553; STREAM_CLOSED",
    "Get \"https://localhost:8443/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=3233, ErrCode=PROTOCOL_ERROR, debug=\"Stream [1,259] has been closed for some time\"",
    "Get \"https://localhost:8443/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=2283, ErrCode=PROTOCOL_ERROR, debug=\"Stream [803] has been closed for some time\"",
    "Get \"https://localhost:8443/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=2581, ErrCode=PROTOCOL_ERROR, debug=\"Stream [1,231] has been closed for some time\"",
    "http2: server sent GOAWAY and closed the connection; LastStreamID=2581, ErrCode=PROTOCOL_ERROR, debug=\"Stream [1,231] has been closed for some time\""
  ]
}
```

The above result is for Apache Tomcat 10.0.0-M8 (Sep 18 2020).