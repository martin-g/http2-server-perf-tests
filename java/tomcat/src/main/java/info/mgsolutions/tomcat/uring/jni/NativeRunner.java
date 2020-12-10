package info.mgsolutions.tomcat.uring.jni;

/**
 *
 */
public class NativeRunner {

	public static void main(String[] args) {
		if (args.length > 0) {
			for (String arg : args) {
				Native.myprint(arg);
			}
		} else {
			Native.myprint("Blah!");
		}
	}

}
