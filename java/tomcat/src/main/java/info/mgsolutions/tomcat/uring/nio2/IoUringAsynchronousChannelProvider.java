package info.mgsolutions.tomcat.uring.nio2;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 *
 */
public class IoUringAsynchronousChannelProvider extends AsynchronousChannelProvider {

	public IoUringAsynchronousChannelProvider() {
		super();
		System.out.println("=============== Using IoUringAsynchronousChannelProvider");
	}

	@Override
	public AsynchronousChannelGroup openAsynchronousChannelGroup(final int nThreads, final ThreadFactory threadFactory) throws IOException {
		return new IoUringAsynchronousChannelGroup(this, Executors.newFixedThreadPool(nThreads, threadFactory), nThreads);
	}

	@Override
	public AsynchronousChannelGroup openAsynchronousChannelGroup(final ExecutorService executor, final int initialSize) throws IOException {
		return new IoUringAsynchronousChannelGroup(this, executor, initialSize);
	}

	@Override
	public AsynchronousServerSocketChannel openAsynchronousServerSocketChannel(final AsynchronousChannelGroup group) throws IOException {
		return new IoUringAsynchronousServerSocketChannel(this, group);
	}

	@Override
	public AsynchronousSocketChannel openAsynchronousSocketChannel(final AsynchronousChannelGroup group) throws IOException {
		return new IoUringAsynchronousSocketChannel(this, group);
	}
}
