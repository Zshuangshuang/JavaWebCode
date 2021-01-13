import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 10:29
 */
public class ServletDemo4 extends HttpServlet {
    //返回一个页面，每秒钟自动刷新一次

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.setIntHeader("Refresh",1);
        Date date = new Date();
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write(date.toString());
        writer.write("</html>");
    }
}
