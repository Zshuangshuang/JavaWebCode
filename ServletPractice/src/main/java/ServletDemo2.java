import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-14 9:16
 */
public class ServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getHeader("Host");
        String user = req.getHeader("User-Agent");
        String contentPath = req.getContextPath();
        String contentType = req.getContentType();
        String url = req.getRequestURI();
        String method = req.getMethod();
        String encoding = req.getCharacterEncoding();
        int contentLength = req.getContentLength();
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<h1>host: </h1>"+host);
        writer.write("<br/>");
        writer.write("<h1>user: </h1>"+user);
        writer.write("<br/>");
        writer.write("<h1>contentPath: </h1>"+contentPath);
        writer.write("<br/>");
        writer.write("<h1>contentType: </h1>"+contentType);
        writer.write("<br/>");
        writer.write("<h1>url: </h1>"+url);
        writer.write("<br/>");
        writer.write("<h1>method: </h1>"+method);
        writer.write("<br/>");
        writer.write("<h1>encoding: <h1>"+encoding);
        writer.write("<br/>");
        writer.write("<h1>contentLength: </h1>"+contentLength);
        writer.write("<br/>");
        writer.write("</html>");
    }
}
