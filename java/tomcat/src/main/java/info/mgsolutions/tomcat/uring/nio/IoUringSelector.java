package info.mgsolutions.tomcat.uring.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 *
 */
public class IoUringSelector extends AbstractSelector {

	/**
	 * Initializes a new instance of this class.
	 *
	 * @param provider The provider that created this selector
	 */
	public IoUringSelector(final SelectorProvider provider) {
		super(provider);
	}

	@Override
	protected void implCloseSelector() throws IOException {
		throw new UnsupportedOperationException("info.mgsolutions.tomcat.uring.nio.IoUringSelector.implCloseSelector");
	}

	@Override
	protected SelectionKey register(final AbstractSelectableChannel ch, final int ops, final Object att) {
		return new IoUringSelectionKey();
	}

	@Override
	public Set<SelectionKey> keys() {
		return null;
	}

	@Override
	public Set<SelectionKey> selectedKeys() {
		return null;
	}

	@Override
	public int selectNow() throws IOException {
		return 0;
	}

	@Override
	public int select(final long timeout) throws IOException {
		return 0;
	}

	@Override
	public int select() throws IOException {
		return 0;
	}

	@Override
	public Selector wakeup() {
		return null;
	}
}
