const http2 = require('http2');
const fs = require('fs');

// const cert_root = '/path/to/certs/';
const cert_root = process.env.TESTBED_HOME + '/etc/tls/';
const server = http2.createSecureServer({
    key: fs.readFileSync(cert_root + 'server.key'),
    cert: fs.readFileSync(cert_root + 'server.crt')
});
server.on('error', (err) => console.error(err));

server.on('stream', (stream, headers) => {
    stream.respond({
        'content-type': 'text/plain; charset=utf-8',
        ':status': 200
    });
    stream.end('Hello world!');
});

server.listen(8080);
