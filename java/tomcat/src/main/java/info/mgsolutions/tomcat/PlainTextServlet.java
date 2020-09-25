package info.mgsolutions.tomcat;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A Servlet that returns plain text
 */
@WebServlet(urlPatterns = PlainTextServlet.URL_PATTERN)
public class PlainTextServlet extends HttpServlet {

	static final String URL_PATTERN = "/testbed/plaintext";
	private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
	private static final byte[] CONTENT = "Hello world!".getBytes(StandardCharsets.UTF_8);
	private static final int CONTENT_LENGTH = CONTENT.length;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setContentLength(CONTENT_LENGTH);
		final ServletOutputStream outputStream = resp.getOutputStream();
		try {
			outputStream.write(CONTENT);
			outputStream.flush();
		} finally {
			outputStream.close();
		}
	}
}
