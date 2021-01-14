import javax.servlet.ServletException;
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
 * Date    2021-01-14 9:02
 */
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aStr = req.getParameter("firstName");
        String bStr = req.getParameter("secondName");
        resp.setContentType("text/html; charset=utf-8");
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("<h1>firstName: </h1>"+aStr);
        writer.write("<br/>");
        writer.write("<h1>secondName: </h1>"+bStr);
        writer.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
