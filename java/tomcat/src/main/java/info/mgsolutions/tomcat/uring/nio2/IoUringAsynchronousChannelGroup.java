package info.mgsolutions.tomcat.uring.nio2;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class IoUringAsynchronousChannelGroup extends AsynchronousChannelGroup {
	private final ExecutorService executor;
	private final int initialSize;

	/**
	 * Initialize a new instance of this class.
	 *
	 * @param provider The asynchronous channel provider for this group
	 * @param executor
	 * @param initialSize
	 */
	protected IoUringAsynchronousChannelGroup(final AsynchronousChannelProvider provider,
	                                          final ExecutorService executor,
	                                          final int initialSize) {
		super(provider);
		this.executor = executor;
		this.initialSize = initialSize;
	}

	@Override
	public boolean isShutdown() {
		return executor.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return executor.isTerminated();
	}

	@Override
	public void shutdown() {
		executor.shutdown();
	}

	@Override
	public void shutdownNow() throws IOException {
		executor.shutdownNow();
	}

	@Override
	public boolean awaitTermination(final long timeout, final TimeUnit unit) throws InterruptedException {
		return executor.awaitTermination(timeout, unit);
	}
}
