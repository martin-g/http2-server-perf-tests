package main

// run with (Fish): env PORT=18080 go run http2-server.go
// run with (Bash): PORT=18080 go run http2-server.go

import (
  "fmt"
  "log"
  "net/http"
  "os"
)

func main() {

  port := os.Getenv("PORT")
  if port == "" {
    log.Fatal("Please specify the HTTP port as environment variable, e.g. env PORT=18080 go run http2-server.go")
  }

  tls_root := "/path/to/tls-certs/"
  srv := &http.Server{Addr: ":" + port, Handler: http.HandlerFunc(handle)}
  log.Fatal(srv.ListenAndServeTLS(tls_root + "server.crt", tls_root + "server.key"))
}

func handle(w http.ResponseWriter, r *http.Request) {
  // log.Printf("Got connection: %s", r.Proto)
  fmt.Fprintf(w, "Hello World")
}
