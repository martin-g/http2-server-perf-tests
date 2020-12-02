package info.mgsolutions.tomcat.uring.nio;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyBoundException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 */
public class IoUringSocketChannel extends SocketChannel {

	private final ReadWriteLock closeLock;
	private final Set<SocketOption<?>> supportedOptions;

	private SocketAddress localAddress;

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The provider that created this channel
	 */
	public IoUringSocketChannel(final SelectorProvider provider) {
		super(provider);
		this.closeLock = new ReentrantReadWriteLock();
		this.supportedOptions = new HashSet<>();
	}

	@Override
	public SocketChannel bind(final SocketAddress local) throws IOException {

		try {
			lock();
			if (localAddress != null)
				throw new AlreadyBoundException();

			InetSocketAddress isa;
			if (local == null) {
				final InetAddress inetAddress = Inet4Address.getByName("0.0.0.0");
				isa = new InetSocketAddress(inetAddress, 0);
			} else {
				isa = (InetSocketAddress) local;
			}
			SecurityManager sm = System.getSecurityManager();
			if (sm != null)
				sm.checkListen(isa.getPort());

			// TODO io_uring-ify
			//			NetHooks.beforeTcpBind(fd, isa.getAddress(), isa.getPort());
			//			Net.bind(family, fd, isa.getAddress(), isa.getPort());
			//			Net.listen(fd, backlog < 1 ? 50 : backlog);
			//			return Net.localAddress(fd);

			localAddress = isa;

		} finally {
			unlock();
		}

		return this;
	}

	@Override
	public <T> SocketChannel setOption(final SocketOption<T> name, final T value) throws IOException {
		return null;
	}

	@Override
	public <T> T getOption(final SocketOption<T> name) throws IOException {
		return null;
	}

	@Override
	public Set<SocketOption<?>> supportedOptions() {
		return supportedOptions;
	}

	@Override
	public SocketChannel shutdownInput() throws IOException {
		// TODO implement me

		return this;
	}

	@Override
	public SocketChannel shutdownOutput() throws IOException {
		// TODO implement me

		return this;
	}

	@Override
	public Socket socket() {
		return null;
	}

	@Override
	public boolean isConnected() {
		return false;
	}

	@Override
	public boolean isConnectionPending() {
		return false;
	}

	@Override
	public boolean connect(final SocketAddress remote) throws IOException {
		return false;
	}

	@Override
	public boolean finishConnect() throws IOException {
		return false;
	}

	@Override
	public SocketAddress getRemoteAddress() throws IOException {
		return null;
	}

	@Override
	public int read(final ByteBuffer dst) throws IOException {
		return 0;
	}

	@Override
	public long read(final ByteBuffer[] dsts, final int offset, final int length) throws IOException {
		return 0;
	}

	@Override
	public int write(final ByteBuffer src) throws IOException {
		return 0;
	}

	@Override
	public long write(final ByteBuffer[] srcs, final int offset, final int length) throws IOException {
		return 0;
	}

	@Override
	public SocketAddress getLocalAddress() throws IOException {
		return localAddress;
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {

	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {

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
