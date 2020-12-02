package info.mgsolutions.tomcat.uring.nio;

import java.io.IOException;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;
import java.nio.channels.spi.SelectorProvider;

/**
 *
 */
public class IoUringSelectorProvider extends SelectorProvider {

	public IoUringSelectorProvider() {
		super();

		System.out.println("=========== Using IoUringSelectorProvider");
	}

	@Override
	public IoUringDatagramChannel openDatagramChannel() throws IOException {
		return openDatagramChannel(StandardProtocolFamily.INET);
	}

	@Override
	public IoUringDatagramChannel openDatagramChannel(final ProtocolFamily family) throws IOException {
		return new IoUringDatagramChannel(this, family);
	}

	@Override
	public IoUringPipe openPipe() throws IOException {
		return new IoUringPipe(this);
	}

	@Override
	public IoUringSelector openSelector() throws IOException {
		return new IoUringSelector(this);
	}

	@Override
	public IoUringServerSocketChannel openServerSocketChannel() throws IOException {
		return new IoUringServerSocketChannel(this);
	}

	@Override
	public IoUringSocketChannel openSocketChannel() throws IOException {
		return new IoUringSocketChannel(this);
	}
}
