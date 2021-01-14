import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
 * Date    2021-01-14 9:46
 */
public class ServletDemo7 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        Cookie cookie1 = new Cookie("name","zss");
        Cookie cookie2 = new Cookie("school","西安工业大学");
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        writer.write("<html>");
        writer.write("<h5>Cookie设置成功</h5>");
        writer.write("</html>");
    }
}
