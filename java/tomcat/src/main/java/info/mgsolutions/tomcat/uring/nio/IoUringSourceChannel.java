package info.mgsolutions.tomcat.uring.nio;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class IoUringSourceChannel extends Pipe.SourceChannel {

	private final FileInputStream in;
	private final ReentrantLock readLock;
	/**
	 * Constructs a new instance of this class.
	 *
	 * @param provider The selector provider
	 * @param fd The file descriptor
	 */
	protected IoUringSourceChannel(final SelectorProvider provider, final FileDescriptor fd) {
		super(provider);
		this.in = new FileInputStream(fd);
		this.readLock = new ReentrantLock();
	}

	@Override
	public long read(final ByteBuffer[] dsts, final int offset, final int length) throws IOException {
		try {
			readLock.lock();
			return in.getChannel().read(dsts, offset, length);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public long read(final ByteBuffer[] dsts) throws IOException {
		try {
			readLock.lock();
			return in.getChannel().read(dsts);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public int read(final ByteBuffer dst) throws IOException {
		try {
			readLock.lock();
			return in.getChannel().read(dst);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {
		try {
			readLock.lock();
			if (in.getChannel().isOpen()) {
				in.close();
			}
		} finally {
			readLock.unlock();
		}
	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {
		try {
			readLock.lock();
			//			synchronized (stateLock) {
			//				if (!isOpen())
			//					throw new ClosedChannelException();
			//				IOUtil.configureBlocking(fd, block);
			//			}
		} finally {
			readLock.unlock();
		}
	}
}
