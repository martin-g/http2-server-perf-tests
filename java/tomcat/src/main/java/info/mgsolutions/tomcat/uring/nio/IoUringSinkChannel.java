package info.mgsolutions.tomcat.uring.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.spi.SelectorProvider;

/**
 *
 */
public class IoUringSinkChannel extends Pipe.SinkChannel {

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The selector provider
	 */
	protected IoUringSinkChannel(final SelectorProvider provider) {
		super(provider);
	}

	@Override
	public long write(final ByteBuffer[] srcs, final int offset, final int length) throws IOException {
		return 0;
	}

	@Override
	public long write(final ByteBuffer[] srcs) throws IOException {
		return 0;
	}

	@Override
	public int write(final ByteBuffer src) throws IOException {
		return 0;
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {

	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {

	}
}
