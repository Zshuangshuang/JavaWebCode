import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 13:21
 */
public class ServletDemo9 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取session，如果用户未访问，就建立session,如果已经访问过，就直接获取
        HttpSession session = req.getSession(true);
        int count = 1;
        if (session.isNew()){
            //第一次访问，会生成sessionId和一个httpSession
            // 同时将sessionId写到浏览器中的cookie
           session.setAttribute("count",count);
        }else {
            //已经访问过，就直接从cookie中获取即可
            count = (Integer) session.getAttribute("count");
            count++;
            session.setAttribute("count",count);
        }
        //返回响应页面
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("count: "+count);
        writer.write("</html>");

    }
}
