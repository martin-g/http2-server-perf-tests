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
			final int result = Native.doWork();
			if (result == 0) {
				System.out.println("Success");
			} else {
				System.err.println("Error: " + result);
			}
		}
	}

}
