package info.mgsolutions.tomcat.uring.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class IoUringAsynchronousServerSocketChannel extends AsynchronousServerSocketChannel {

	private final AsynchronousChannelGroup group;
	private final ReadWriteLock closeLock;
	private final Set<SocketOption<?>> supportedOptions;

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The provider that created this channel
	 * @param group
	 */
	protected IoUringAsynchronousServerSocketChannel(final AsynchronousChannelProvider provider, final AsynchronousChannelGroup group) {
		super(provider);
		this.group = group;
		this.closeLock = new ReentrantReadWriteLock();
		this.supportedOptions = new HashSet<>();
	}

	@Override
	public IoUringAsynchronousServerSocketChannel bind(final SocketAddress local, final int backlog) throws IOException {

		try {
			lock();
			InetSocketAddress isa = (local == null) ? new InetSocketAddress(0) : (InetSocketAddress) local;
			// TODO bind isa

		} finally {
			unlock();
		}

		return this;
	}

	@Override
	public <T> IoUringAsynchronousServerSocketChannel setOption(final SocketOption<T> name, final T value) throws IOException {

		try {
			lock();

			// TODO set option

		} finally {
			unlock();
		}

		return this;
	}

	@Override
	public <T> T getOption(final SocketOption<T> name) throws IOException {

		try {
			lock();

			// TODO get option
			return null;

		} finally {
			unlock();
		}
	}

	@Override
	public Set<SocketOption<?>> supportedOptions() {
		return Collections.unmodifiableSet(supportedOptions);
	}

	@Override
	public <A> void accept(final A attachment, final CompletionHandler<AsynchronousSocketChannel, ? super A> handler) {

	}

	@Override
	public Future<AsynchronousSocketChannel> accept() {
		return null;
	}

	@Override
	public SocketAddress getLocalAddress() throws IOException {
		return null;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public void close() throws IOException {
		try {
			closeLock.writeLock().lock();

			// TODO close FDs

		} finally {
			closeLock.writeLock().unlock();
		}
	}

	private void lock() throws IOException {
		closeLock.readLock().lock();
		if (!isOpen())
			throw new ClosedChannelException();
	}

	private void unlock() {
		closeLock.readLock().unlock();
	}
}
