package info.mgsolutions.tomcat.onenio;

import one.nio.net.SelectableJavaSocket;
import one.nio.net.Socket;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.util.net.SocketEvent;
import org.apache.tomcat.util.net.SocketProcessorBase;
import org.apache.tomcat.util.net.SocketWrapperBase;

import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.nio.channels.SocketChannel;

/**
 *
 */
public class OneNioEndpoint extends org.apache.tomcat.util.net.AbstractJsseEndpoint<OneNioChannel, SocketChannel> {

	private static final Log log = LogFactory.getLog(OneNioEndpoint.class);

	@Override
	protected NetworkChannel getServerSocket() {
		try {
			final Socket serverSocket = SelectableJavaSocket.createServerSocket();
			return serverSocket.getse;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected SocketProcessorBase<OneNioChannel> createSocketProcessor(final SocketWrapperBase<OneNioChannel> socketWrapper,
	                                                                   final SocketEvent event) {
		return null;
	}

	@Override
	public void bind() throws Exception {

	}

	@Override
	public void startInternal() throws Exception {

	}

	@Override
	public void stopInternal() throws Exception {

	}

	@Override
	protected Log getLog() {
		return log;
	}

	@Override
	protected void doCloseServerSocket() throws IOException {

	}

	@Override
	protected SocketChannel serverSocketAccept() throws Exception {
		return null;
	}

	@Override
	protected boolean setSocketOptions(final SocketChannel socket) {
		return false;
	}

	@Override
	protected void destroySocket(final SocketChannel socket) {

	}
}
