package info.mgsolutions.tomcat.onenio;

import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 *
 */
public class Http11OneNioProtocol extends AbstractHttp11JsseProtocol<OneNioChannel> {

	private static final Log log = LogFactory.getLog(Http11OneNioProtocol.class);

	public Http11OneNioProtocol() {
		super(new OneNioEndpoint());
	}

	@Override
	protected Log getLog() {
		return log;
	}

	@Override
	protected String getNamePrefix() {
		if (isSSLEnabled()) {
			return "https-" + getSslImplementationShortName()+ "-one-nio";
		} else {
			return "http-one-nio";
		}
	}

}
