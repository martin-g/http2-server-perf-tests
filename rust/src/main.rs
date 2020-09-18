use actix_web::{web, App, HttpRequest, HttpServer, Responder};
use openssl::ssl::{SslAcceptor, SslFiletype, SslMethod};

async fn index(_req: HttpRequest) -> impl Responder {
    "Hello world!"
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    let mut builder = SslAcceptor::mozilla_intermediate(SslMethod::tls()).unwrap();
    let cert_root = "/path/to/certs/"
    builder
        .set_private_key_file(cert_root.to_owned() + "server.key", SslFiletype::PEM)
        .unwrap();
    builder.set_certificate_chain_file(cert_root.to_owned() + "server.crt").unwrap();

    HttpServer::new(|| App::new().route("/", web::get().to(index)))
        .bind_openssl("127.0.0.1:18080", builder)?
        .run()
        .await
}
