import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-14 9:25
 */
public class ServletDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        Enumeration<String> headerNames = req.getHeaderNames();
        writer.write("<html>");
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            writer.write(header+": "+req.getHeader(header));
            writer.write("<br/>");
        }
        writer.write("</html>");
    }
}
