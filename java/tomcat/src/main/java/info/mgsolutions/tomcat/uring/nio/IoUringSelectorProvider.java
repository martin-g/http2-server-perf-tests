package info.mgsolutions.tomcat.uring.nio;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.ProtocolFamily;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 *
 */
public class IoUringSelectorProvider extends SelectorProvider {

//	private final RingBuffer ringBuffer;

	private static volatile SelectorProvider previousSelectorProvider;

	private final SelectorProvider delegate;

	public IoUringSelectorProvider(final SelectorProvider delegate) {
		super();
		this.delegate = delegate;

		System.out.println("=========== Using IoUringSelectorProvider");
//		ringBuffer = Native.createRingBuffer();
	}

	public static synchronized void install() {

		SelectorProvider delegate = SelectorProvider.provider();

		if (null == delegate) {
			throw new RuntimeException("No default SelectorProvider!");
		}

		if (null == previousSelectorProvider && !IoUringSelectorProvider.class.equals(delegate.getClass())) {
			previousSelectorProvider = delegate;
		}

		if (IoUringSelectorProvider.class.equals(delegate.getClass())) {
			return;
		}

		final IoUringSelectorProvider selectorProvider = new IoUringSelectorProvider(delegate);

		try {
			final Class<?> selectorProviderHolderClass = Class.forName("java.nio.channels.spi.SelectorProvider$Holder");
			final Field instanceField = getField(selectorProviderHolderClass, "INSTANCE");
			setFieldValue(instanceField, null, selectorProvider);
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}

	public static void uninstall() {

		if (null == previousSelectorProvider) {
			return;
		}

		try {
			final Class<?> selectorProviderHolderClass = Class.forName("java.nio.channels.spi.SelectorProvider$Holder");
			final Field instanceField = getField(selectorProviderHolderClass, "INSTANCE");
			setFieldValue(instanceField, null, previousSelectorProvider);
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}

	private static void setFieldValue(Field field, Object target, Object value) throws IllegalAccessException, NoSuchFieldException {
		final Field modifiersField = getField(Field.class, "modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL );
		final boolean canAccess = field.isAccessible();
		try {
			field.setAccessible(true);
			field.set(target, value);
		} finally {
			field.setAccessible(canAccess);
			modifiersField.setInt(field, field.getModifiers() & Modifier.FINAL );
		}
	}

	private static Field getField(Class<?> cls, String fieldName) throws NoSuchFieldException
	{
		try {
			return cls.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			try {
				Method getDeclaredFields0 = Class.class.getDeclaredMethod("getDeclaredFields0", boolean.class);
				getDeclaredFields0.setAccessible(true);
				Field[] fields = (Field[]) getDeclaredFields0.invoke(cls, false);
				for (Field field : fields) {
					if (fieldName.equals(field.getName())) {
						return field;
					}
				}
			}
			catch (ReflectiveOperationException ex) {
				e.addSuppressed(ex);
			}
			throw e;
		}
	}

	@Override
	public DatagramChannel openDatagramChannel() throws IOException {
		return delegate.openDatagramChannel();
	}

	@Override
	public DatagramChannel openDatagramChannel(final ProtocolFamily family) throws IOException {
		return delegate.openDatagramChannel(family);
	}

	@Override
	public Pipe openPipe() throws IOException {
		return delegate.openPipe();
	}

	@Override
	public AbstractSelector openSelector() throws IOException {
		return delegate.openSelector();
	}

	@Override
	public ServerSocketChannel openServerSocketChannel() throws IOException {
		final ServerSocketChannel delegateServerSocketChannel = delegate.openServerSocketChannel();
		return new IoUringServerSocketChannel(this, delegateServerSocketChannel);
	}

	@Override
	public SocketChannel openSocketChannel() throws IOException {
		return new IoUringSocketChannel(this);
	}

//	RingBuffer getRingBuffer() {
//		return ringBuffer;
//	}
}
