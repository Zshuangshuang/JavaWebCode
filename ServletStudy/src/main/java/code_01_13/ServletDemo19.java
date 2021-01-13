package code_01_13;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 19:44
 */
public class ServletDemo19 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1)通过req.getSession()来判断当前session是否已经创建
        // 如果创建了就直接从cookie中获取，并返回一个访问次数的页面
        // 如果没有创建就创建一个session对象
        HttpSession httpSession = req.getSession();
        int count = 1;
        if (httpSession.isNew()){
            httpSession.setAttribute("count",count);
        }else {
            count = (Integer) httpSession.getAttribute("count");
            count++;
            httpSession.setAttribute("count",count);
        }
        //2)返回响应页面
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<h1>count: </h1>"+count);
        writer.write("</html>");
    }
}
