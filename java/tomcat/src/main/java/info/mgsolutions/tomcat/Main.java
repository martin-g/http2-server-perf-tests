package info.mgsolutions.tomcat;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http2.Http2Protocol;

import java.io.File;

public class Main {

    private static final String TESTBED_HOME = System.getenv("TESTBED_HOME");

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        StandardContext ctx = (StandardContext) tomcat.addContext("", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "plaintext", PlainTextServlet.class.getName());
        ctx.addServletMappingDecoded("/testbed/plaintext", "plaintext");

        final Connector connector = tomcat.getConnector();
        final boolean h2c = Boolean.getBoolean("tomcat.h2c");
        final boolean http2 = Boolean.getBoolean("tomcat.http2");
        boolean tls = Boolean.getBoolean("tomcat.tls");
        if (h2c || http2) {
            connector.addUpgradeProtocol(new Http2Protocol());
            if (http2) {
                tls = true;
            }
        }

        if (tls) {
            connector.setScheme("https");
            connector.setSecure(true);
            connector.setProperty("sslProtocol", "TLS");
            connector.setProperty("SSLEnabled", "true");
            connector.setProperty("SSLCertificateFile", TESTBED_HOME + "/etc/tls/server.pem");
            connector.setProperty("SSLCertificateKeyFile", TESTBED_HOME + "/etc/tls/server.key");
        }

        System.out.println("Starting on port: " + connector);
        tomcat.start();
        System.out.println("Started");
        tomcat.getServer().await();
        System.out.println("Stopped");
    }
}
