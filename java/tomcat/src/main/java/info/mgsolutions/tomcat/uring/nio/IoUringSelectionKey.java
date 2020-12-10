package info.mgsolutions.tomcat.uring.nio;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 *
 */
public class IoUringSelectionKey extends SelectionKey {
	@Override
	public SelectableChannel channel() {
		return null;
	}

	@Override
	public Selector selector() {
		return null;
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public void cancel() {

	}

	@Override
	public int interestOps() {
		return 0;
	}

	@Override
	public SelectionKey interestOps(final int ops) {
		return null;
	}

	@Override
	public int readyOps() {
		return 0;
	}
}
