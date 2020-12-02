package info.mgsolutions.tomcat.uring.nio;

import io.netty.incubator.channel.uring.IOUring;
import sun.nio.ch.IOUtil;
import sun.nio.ch.SinkChannelImpl;
import sun.nio.ch.SourceChannelImpl;

import java.io.FileDescriptor;
import java.nio.channels.Pipe;
import java.nio.channels.spi.SelectorProvider;

/**
 *
 */
public class IoUringPipe extends Pipe {
	private final IoUringSourceChannel source;
	private final IoUringSinkChannel sink;

	public IoUringPipe(SelectorProvider sp) {
		long pipeFds = IOUtil.makePipe(true);
		int readFd = (int) (pipeFds >>> 32);
		int writeFd = (int) pipeFds;
		FileDescriptor sourcefd = new FileDescriptor();
		IOUring.setfdVal(sourcefd, readFd);
		source = new IoUringSourceChannel(sp, sourcefd);
		FileDescriptor sinkfd = new FileDescriptor();
		IOUtil.setfdVal(sinkfd, writeFd);
		sink = new SinkChannelImpl(sp, sinkfd);

		source = null;
		sink = null;
	}

	@Override
	public SourceChannel source() {
		return source;
	}

	@Override
	public SinkChannel sink() {
		return sink;
	}
}
