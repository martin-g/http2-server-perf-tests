package info.mgsolutions.tomcat.uring.jni;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.Library;
import org.fusesource.hawtjni.runtime.MethodFlag;

/**
 *
 */
@JniClass
public class Native {

	private static final Library LIBRARY = new Library("tomcat_embedded", Native.class);
	static {
		LIBRARY.load();
//		init();
	}

//	@JniMethod(flags={MethodFlag.CONSTANT_INITIALIZER})
//	private static native void init();

	@JniMethod
	public static native void myprint(String text);

	@JniMethod
	public static native int doWork();


	//	@JniClass(flags = {ClassFlag.STRUCT})
//	public static class Iovec {
//
//		static {
//			LIBRARY.load();
//			init();
//		}
//
//		@JniMethod(flags={MethodFlag.CONSTANT_INITIALIZER})
//		private static native void init();
//
//		long iov_base;    /* Starting address */
//		int iov_len;      /* Number of bytes to transfer */
//	}
//
//	@JniClass(flags = {ClassFlag.STRUCT})
//	public static class Request {
//		static {
//			LIBRARY.load();
//			init();
//		}
//
//		@JniMethod(flags={MethodFlag.CONSTANT_INITIALIZER})
//		private static native void init();
//
//		int event_type;
//		int iovec_count;
//		int client_socket;
//		Iovec[] iov;
//	}
}
