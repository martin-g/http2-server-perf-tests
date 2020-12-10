package info.mgsolutions.tomcat.uring.nio;

import sun.misc.Unsafe;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.ProtocolFamily;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardProtocolFamily;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class IoUringServerSocketChannel extends ServerSocketChannel {

	private final ReadWriteLock closeLock;
	private final ProtocolFamily protocolFamily;
	private final FileDescriptor fd;
	private final Set<SocketOption<?>> supportedOptions;
	private final ServerSocketChannel delegate;
	private SocketAddress localAddress;

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider  The EPoll provider
	 * @param delegate
	 */
	public IoUringServerSocketChannel(final IoUringSelectorProvider provider, final ServerSocketChannel delegate) {
		super(provider);
		this.delegate = delegate;
		this.closeLock = new ReentrantReadWriteLock();
		this.protocolFamily = StandardProtocolFamily.INET; // TODO add support for IPv6
		fd = null; // TODO get FD from io_uring
		this.supportedOptions = new HashSet<>();
	}

	@Override
	public ServerSocketChannel bind(final SocketAddress local, final int backlog) throws IOException {
		try {
			delegate.bind(local, backlog);
//			lock();
//			if (localAddress != null) throw new AlreadyBoundException();
//
//			InetSocketAddress isa;
//			if (local == null) {
//				final InetAddress inetAddress = protocolFamily == StandardProtocolFamily.INET6
//				                                ? Inet6Address.getByName("::")
//				                                : Inet4Address.getByName("0.0.0.0");
//				isa = new InetSocketAddress(inetAddress, 0);
//			} else {
//				isa = (InetSocketAddress) local;
//			}
//			SecurityManager sm = System.getSecurityManager();
//			if (sm != null)
//				sm.checkListen(isa.getPort());
//
//			socket().bind(isa);

			// TODO io_uring-ify
//			NetHooks.beforeTcpBind(fd, isa.getAddress(), isa.getPort());
//			Net.bind(family, fd, isa.getAddress(), isa.getPort());
//			Net.listen(fd, backlog < 1 ? 50 : backlog);
//			return Net.localAddress(fd);

//			localAddress = isa;

		} finally {
			unlock();
		}

		return this;
	}

	@Override
	public <T> ServerSocketChannel setOption(final SocketOption<T> name, final T value) throws IOException {
		return delegate.setOption(name, value);
	}

	@Override
	public <T> T getOption(final SocketOption<T> name) throws IOException {
		return delegate.getOption(name);
	}

	@Override
	public Set<SocketOption<?>> supportedOptions() {
		return delegate.supportedOptions();
	}

	@Override
	public ServerSocket socket() {
		return delegate.socket();
	}

	@Override
	public SocketChannel accept() throws IOException {
		return delegate.accept();
	}

	@Override
	public SocketAddress getLocalAddress() throws IOException {
		return delegate.getLocalAddress();
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {
		delegate.close();
	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {
		delegate.configureBlocking(block);
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
