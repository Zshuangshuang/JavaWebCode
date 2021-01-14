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
 * Date    2021-01-14 9:54
 */
public class ServletDemo9 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(true);
        int count = 1;
        if (httpSession.isNew()){
            httpSession.setAttribute("count",count);
        }else {
            count = (Integer)httpSession.getAttribute("count");
            count++;
            httpSession.setAttribute("count",count);
        }
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<h3>count: "+count+"</h3>");
        writer.write("</html>");
    }
}
