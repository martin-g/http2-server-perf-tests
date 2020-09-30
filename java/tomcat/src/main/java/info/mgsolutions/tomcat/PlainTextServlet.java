package info.mgsolutions.tomcat;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		final ServletOutputStream outputStream = resp.getOutputStream();
		try {
			outputStream.write(CONTENT_AS_BYTES);
			outputStream.flush();
		} finally {
			outputStream.close();
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		resp.setContentType(CONTENT_TYPE);
		final Writer out = resp.getWriter();
		final String payload = req.getParameter("payload");
		try {
			if (payload != null) {
				resp.setContentLength(payload.length());
				out.write(payload);
			} else {
				resp.setContentLength(CONTENT_LENGTH);
				out.write(CONTENT_AS_STRING);
			}
			out.flush();
		} finally {
			out.close();
		}
	}
}
