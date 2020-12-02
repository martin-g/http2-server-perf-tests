package info.mgsolutions.tomcat.uring.nio;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ProtocolFamily;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 *
 */
public class IoUringDatagramChannel extends DatagramChannel {

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The provider that created this channel
	 * @param family
	 */
	public IoUringDatagramChannel(final SelectorProvider provider, final ProtocolFamily family) throws IOException {
		super(provider);
	}

	@Override
	public DatagramChannel bind(final SocketAddress local) throws IOException {
		return null;
	}

	@Override
	public <T> DatagramChannel setOption(final SocketOption<T> name, final T value) throws IOException {
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
	public DatagramSocket socket() {
		return null;
	}

	@Override
	public boolean isConnected() {
		return false;
	}

	@Override
	public DatagramChannel connect(final SocketAddress remote) throws IOException {
		return null;
	}

	@Override
	public DatagramChannel disconnect() throws IOException {
		return null;
	}

	@Override
	public SocketAddress getRemoteAddress() throws IOException {
		return null;
	}

	@Override
	public SocketAddress receive(final ByteBuffer dst) throws IOException {
		return null;
	}

	@Override
	public int send(final ByteBuffer src, final SocketAddress target) throws IOException {
		return 0;
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
		return null;
	}

	@Override
	public MembershipKey join(final InetAddress group, final NetworkInterface interf) throws IOException {
		return null;
	}

	@Override
	public MembershipKey join(final InetAddress group, final NetworkInterface interf, final InetAddress source) throws IOException {
		return null;
	}

	@Override
	protected void implCloseSelectableChannel() throws IOException {

	}

	@Override
	protected void implConfigureBlocking(final boolean block) throws IOException {

	}
}
