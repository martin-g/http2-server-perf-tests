package info.mgsolutions.tomcat;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * A Servlet that returns plain text
 */
@WebServlet(urlPatterns = PlainTextServlet.URL_PATTERN)
public class PlainTextServlet extends HttpServlet {

	static final String URL_PATTERN = "/testbed/plaintext";
	private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
	private static final String CONTENT_AS_STRING = "Hello world!";
	private static final byte[] CONTENT_AS_BYTES = CONTENT_AS_STRING.getBytes(StandardCharsets.UTF_8);
	private static final int CONTENT_LENGTH = CONTENT_AS_BYTES.length;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setContentLength(CONTENT_LENGTH);
		try (ServletOutputStream outputStream = resp.getOutputStream()) {
			outputStream.write(CONTENT_AS_BYTES);
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		resp.setContentType(CONTENT_TYPE);
		try (Writer out = resp.getWriter()) {
			final String payload = req.getParameter("payload");
			if (payload != null) {
				resp.setContentLength(payload.length());
				out.write(payload);
			} else {
				resp.setContentLength(CONTENT_LENGTH);
				out.write(CONTENT_AS_STRING);
			}
		}
	}
}
