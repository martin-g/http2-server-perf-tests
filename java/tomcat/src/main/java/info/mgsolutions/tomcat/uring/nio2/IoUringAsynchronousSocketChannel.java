package info.mgsolutions.tomcat.uring.nio2;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class IoUringAsynchronousSocketChannel extends AsynchronousSocketChannel {

	private final AsynchronousChannelGroup group;

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The provider that created this channel
	 * @param group
	 */
	protected IoUringAsynchronousSocketChannel(final AsynchronousChannelProvider provider, final AsynchronousChannelGroup group) {
		super(provider);
		this.group = group;
	}

	@Override
	public AsynchronousSocketChannel bind(final SocketAddress local) throws IOException {
		return null;
	}

	@Override
	public <T> AsynchronousSocketChannel setOption(final SocketOption<T> name, final T value) throws IOException {
		return null;
	}

	@Override
	public <T> T getOption(final SocketOption<T> name) throws IOException {
		return null;
	}

	@Override
	public Set<SocketOption<?>> supportedOptions() {
		return null;
	}

	@Override
	public AsynchronousSocketChannel shutdownInput() throws IOException {
		return null;
	}

	@Override
	public AsynchronousSocketChannel shutdownOutput() throws IOException {
		return null;
	}

	@Override
	public SocketAddress getRemoteAddress() throws IOException {
		return null;
	}

	@Override
	public <A> void connect(final SocketAddress remote, final A attachment, final CompletionHandler<Void, ? super A> handler) {

	}

	@Override
	public Future<Void> connect(final SocketAddress remote) {
		return null;
	}

	@Override
	public <A> void read(final ByteBuffer dst, final long timeout, final TimeUnit unit, final A attachment,
	                     final CompletionHandler<Integer, ? super A> handler) {

	}

	@Override
	public Future<Integer> read(final ByteBuffer dst) {
		return null;
	}

	@Override
	public <A> void read(final ByteBuffer[] dsts, final int offset, final int length, final long timeout, final TimeUnit unit, final A attachment,
	                     final CompletionHandler<Long, ? super A> handler) {

	}

	@Override
	public <A> void write(final ByteBuffer src, final long timeout, final TimeUnit unit, final A attachment,
	                      final CompletionHandler<Integer, ? super A> handler) {

	}

	@Override
	public Future<Integer> write(final ByteBuffer src) {
		return null;
	}

	@Override
	public <A> void write(final ByteBuffer[] srcs, final int offset, final int length, final long timeout, final TimeUnit unit, final A attachment,
	                      final CompletionHandler<Long, ? super A> handler) {

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

	}
	//	public IoUringAsynchronousSocketChannel(final AsynchronousChannelGroup group) {super();}
}
