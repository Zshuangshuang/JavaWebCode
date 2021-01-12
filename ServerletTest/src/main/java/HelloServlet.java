import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-12 10:54
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //实现一个简单的hello world,请求不管是什么，都固定返回一个hello world
        //下面的语句是实现将一个字符串写到Http响应的Body中去
        resp.getWriter().write("<h1>hello servlet</h1>");
    }
}
