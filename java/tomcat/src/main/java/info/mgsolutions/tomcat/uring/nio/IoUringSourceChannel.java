package info.mgsolutions.tomcat.uring.nio;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.spi.SelectorProvider;

/**
 *
 */
public class IoUringSourceChannel extends Pipe.SourceChannel {

	private final FileDescriptor fd;

	/**
	 * Constructs a new instance of this class.
	 *
	 * @param provider The selector provider
	 * @param fd The file descriptor
	 */
	protected IoUringSourceChannel(final SelectorProvider provider, final FileDescriptor fd) {
		super(provider);
		this.fd = fd;
	}

	@Override
	public long read(final ByteBuffer[] dsts, final int offset, final int length) throws IOException {
		return 0;
	}

	@Override
	public long read(final ByteBuffer[] dsts) throws IOException {
		return 0;
	}

	@Override
	public int read(final ByteBuffer dst) throws IOException {
		return 0;
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {

	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {

	}
}
