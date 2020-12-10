package info.mgsolutions.tomcat.uring.nio;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class IoUringSinkChannel extends Pipe.SinkChannel {

	private final FileOutputStream out;
	private final ReentrantLock writeLock;

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The selector provider
	 * @param fd
	 */
	protected IoUringSinkChannel(final SelectorProvider provider, final FileDescriptor fd) {
		super(provider);
		this.out = new FileOutputStream(fd);
		writeLock = new ReentrantLock();
	}

	@Override
	public long write(final ByteBuffer[] srcs, final int offset, final int length) throws IOException {
		try {
			writeLock.lock();
			checkOpened();
			return out.getChannel().write(srcs, offset, length);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public long write(final ByteBuffer[] srcs) throws IOException {
		try {
			writeLock.lock();
			checkOpened();
			return out.getChannel().write(srcs);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public int write(final ByteBuffer src) throws IOException {
		try {
			writeLock.lock();
			checkOpened();
			return out.getChannel().write(src);
		} finally {
			writeLock.unlock();
		}
	}

	private void checkOpened() {
		if (!out.getChannel().isOpen()) {
			throw new RuntimeException("The channel is closed");
		}
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {
		try {
			writeLock.lock();
			if (out.getChannel().isOpen()) {
				out.close();
			}
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {
		try {
			writeLock.lock();
			//			synchronized (stateLock) {
			//				if (!isOpen())
			//					throw new ClosedChannelException();
			//				IOUtil.configureBlocking(fd, block);
			//			}
		} finally {
			writeLock.unlock();
		}
	}
}
