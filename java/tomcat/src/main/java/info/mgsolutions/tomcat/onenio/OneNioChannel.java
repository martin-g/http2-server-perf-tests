package info.mgsolutions.tomcat.onenio;

import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketBufferHandler;

/**
 *
 */
public class OneNioChannel extends NioChannel {



	public OneNioChannel(final SocketBufferHandler bufHandler) {
		super(bufHandler);
	}
}
