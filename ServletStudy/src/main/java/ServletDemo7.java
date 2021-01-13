import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 10:53
 */
public class ServletDemo7 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //给浏览器写入cookie
        //1)先创建一个cookie对象
        Cookie userName = new Cookie("userName","zss");
        Cookie age = new Cookie("age","18");
        //2)把cookie放入响应中
        resp.addCookie(userName);
        resp.addCookie(age);
        //3)创建一个响应报文
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("返回Cookie成功");
    }
}
