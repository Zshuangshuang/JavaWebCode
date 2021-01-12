import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-12 15:41
 */
public class CalcServlet extends HelloServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //读取url中的参数内容，将读取到的内容进行相加
        //url形如:a=10&b=20
        String aStr = req.getParameter("a");
        String bStr = req.getParameter("b");
        int a = Integer.parseInt(aStr);
        int b = Integer.parseInt(bStr);
        int result = a+b;
        resp.getWriter().write(String.format("<h1>result = %d</h1>",result));
    }
}
