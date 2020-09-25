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
            maxConcurrentStreams="16" 
            maxConcurrentStreamExecution="16"/>
        
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
    "total": 19871875621671,
    "mean": 149510398,
    "50th": 70284,
    "90th": 1570958,
    "95th": 269427222,
    "99th": 6631909254,
    "max": 10519308502,
    "min": 15289
  },
  "bytes_in": {
    "total": 9342422,
    "mean": 70.28975344774402
  },
  "bytes_out": {
    "total": 0,
    "mean": 0
  },
  "earliest": "2020-09-18T10:45:12.121772152+03:00",
  "latest": "2020-09-18T10:45:32.154013058+03:00",
  "end": "2020-09-18T10:45:32.418170816+03:00",
  "duration": 20032240906,
  "wait": 264157758,
  "requests": 132913,
  "rate": 6634.954153341391,
  "throughput": 408.7917338121911,
  "success": 0.06242429258236591,
  "status_codes": {
    "0": 124616,
    "200": 8297
  },
  "errors": [
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=147, ErrCode=PROTOCOL_ERROR, debug=\"Stream [117] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=59, ErrCode=PROTOCOL_ERROR, debug=\"Stream [29] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=79, ErrCode=PROTOCOL_ERROR, debug=\"Stream [49] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=119, ErrCode=PROTOCOL_ERROR, debug=\"Stream [89] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=59, ErrCode=PROTOCOL_ERROR, debug=\"Stream [25] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=317, ErrCode=PROTOCOL_ERROR, debug=\"Stream [279] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=149, ErrCode=PROTOCOL_ERROR, debug=\"Stream [109] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=99, ErrCode=PROTOCOL_ERROR, debug=\"Stream [69] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=381, ErrCode=PROTOCOL_ERROR, debug=\"Stream [353] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=287, ErrCode=PROTOCOL_ERROR, debug=\"Stream [257] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=165, ErrCode=PROTOCOL_ERROR, debug=\"Stream [135] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=219, ErrCode=PROTOCOL_ERROR, debug=\"Stream [189] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=313, ErrCode=PROTOCOL_ERROR, debug=\"Stream [283] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=223, ErrCode=PROTOCOL_ERROR, debug=\"Stream [193] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=141, ErrCode=PROTOCOL_ERROR, debug=\"Stream [113] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": write tcp 127.0.0.1:58927->127.0.0.1:18080: write: broken pipe",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=311, ErrCode=PROTOCOL_ERROR, debug=\"Stream [291] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=239, ErrCode=PROTOCOL_ERROR, debug=\"Stream [207] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=427, ErrCode=PROTOCOL_ERROR, debug=\"Stream [391] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": dial tcp 0.0.0.0:0->127.0.0.1:18080: socket: too many open files",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=233, ErrCode=PROTOCOL_ERROR, debug=\"Stream [191] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=1919, ErrCode=PROTOCOL_ERROR, debug=\"Stream [1,883] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=691, ErrCode=PROTOCOL_ERROR, debug=\"Stream [667] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=265, ErrCode=PROTOCOL_ERROR, debug=\"Stream [243] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=47, ErrCode=PROTOCOL_ERROR, debug=\"Stream [33] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=43, ErrCode=PROTOCOL_ERROR, debug=\"Stream [21] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=213, ErrCode=PROTOCOL_ERROR, debug=\"Stream [183] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=121, ErrCode=PROTOCOL_ERROR, debug=\"Stream [97] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=119, ErrCode=PROTOCOL_ERROR, debug=\"Stream [95] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=87, ErrCode=PROTOCOL_ERROR, debug=\"Stream [59] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=121, ErrCode=PROTOCOL_ERROR, debug=\"Stream [95] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=143, ErrCode=PROTOCOL_ERROR, debug=\"Stream [115] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=439, ErrCode=PROTOCOL_ERROR, debug=\"Stream [409] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=261, ErrCode=PROTOCOL_ERROR, debug=\"Stream [241] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=259, ErrCode=PROTOCOL_ERROR, debug=\"Stream [229] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=249, ErrCode=PROTOCOL_ERROR, debug=\"Stream [219] has been closed for some time\"",
    "http2: server sent GOAWAY and closed the connection; LastStreamID=249, ErrCode=PROTOCOL_ERROR, debug=\"Stream [219] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=229, ErrCode=PROTOCOL_ERROR, debug=\"Stream [201] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=301, ErrCode=PROTOCOL_ERROR, debug=\"Stream [271] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=201, ErrCode=PROTOCOL_ERROR, debug=\"Stream [171] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=183, ErrCode=PROTOCOL_ERROR, debug=\"Stream [159] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=267, ErrCode=PROTOCOL_ERROR, debug=\"Stream [237] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=259, ErrCode=PROTOCOL_ERROR, debug=\"Stream [233] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=453, ErrCode=PROTOCOL_ERROR, debug=\"Stream [431] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=219, ErrCode=PROTOCOL_ERROR, debug=\"Stream [191] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=355, ErrCode=PROTOCOL_ERROR, debug=\"Stream [329] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=81, ErrCode=PROTOCOL_ERROR, debug=\"Stream [59] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=619, ErrCode=PROTOCOL_ERROR, debug=\"Stream [589] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=221, ErrCode=PROTOCOL_ERROR, debug=\"Stream [193] has been closed for some time\"",
    "Get \"https://localhost:18080/examples/index.html\": http2: server sent GOAWAY and closed the connection; LastStreamID=339, ErrCode=PROTOCOL_ERROR, debug=\"Stream [309] has been closed for some time\""
  ]
}

```

The above result is for Apache Tomcat 10.0.0-M8 (Sep 18 2020).