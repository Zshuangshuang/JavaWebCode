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
 * Date    2021-01-13 8:59
 */
public class ServletDemo1  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String encoding = req.getCharacterEncoding();
        String contentType = req.getContentType();
        String contentPath = req.getContextPath();
        String user = req.getHeader("User-Agent");
        String host = req.getHeader("Host");
        String method = req.getMethod();
        String protocol = req.getProtocol();
        String queryString = req.getQueryString();
        String url = req.getRequestURI();
        int contentLength = req.getContentLength();
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("encoding: "+encoding);
        writer.write("</br>");
        writer.write("contentType: "+contentType);
        writer.write("</br>");
        writer.write("contentPath: "+contentPath);
        writer.write("</br>");
        writer.write("user: "+user);
        writer.write("</br>");
        writer.write("host: "+host);
        writer.write("</br>");
        writer.write("method: "+method);
        writer.write("</br>");
        writer.write("protocol: "+protocol);
        writer.write("</br>");
        writer.write("queryString: "+queryString);
        writer.write("</br>");
        writer.write("url: "+url);
        writer.write("</br>");
        writer.write("contentLength: "+contentLength);
        writer.write("</br>");
        writer.write("</html>");
    }
}
