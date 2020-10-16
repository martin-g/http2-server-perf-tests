package info.mgsolutions.tomcat;

import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11AprProtocol;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.coyote.http2.Http2Protocol;

import java.io.File;

public class TomcatEmbedded {

    private static final String TESTBED_HOME = System.getenv("TESTBED_HOME");
    private static final String PORT = System.getenv("PORT");
    private static final String MAX_THREADS = System.getenv("TOMCAT_MAX_THREADS");

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();

        StandardContext ctx = (StandardContext) tomcat.addContext("", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "plaintext", PlainTextServlet.class.getName());
        ctx.addServletMappingDecoded("/testbed/plaintext", "plaintext");

        final Wrapper async = Tomcat.addServlet(ctx, "asyncplaintext", AsyncPlainTextServlet.class.getName());
        async.setAsyncSupported(true);
        ctx.addServletMappingDecoded("/testbed/asyncplaintext", "asyncplaintext");

        String protocolName = System.getenv("TOMCAT_PROTOCOL");
        if (protocolName == null) {
            protocolName = Http11NioProtocol.class.getName();
        }
        System.out.println("=== Protocol: " + protocolName);
        Connector connector = new Connector(protocolName);
        if (Http11AprProtocol.class.getName().equals(protocolName)) {
            connector.addLifecycleListener(new AprLifecycleListener());
        }
        tomcat.setConnector(connector);
        connector.setPort(Integer.parseInt(PORT, 10));
        connector.setProperty("maxThreads", MAX_THREADS != null ? MAX_THREADS : "200");

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

        System.out.println("=== Starting on port: " + connector);
        tomcat.start();
        System.out.println("=== Started");
        tomcat.getServer().await();
        System.out.println("=== Stopped");
    }
}
