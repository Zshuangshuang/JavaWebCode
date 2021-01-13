package code_01_13;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * Author:ZouDouble
 * Description:实现每个一秒刷新一次页面
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 16:11
 */
public class ServletDemo14 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        Date date = new Date();
        resp.setIntHeader("Refresh",1);
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write(date.toString());
        writer.write("</html>");
    }
}
