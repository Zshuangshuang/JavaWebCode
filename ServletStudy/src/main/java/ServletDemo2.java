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
 * Date    2021-01-13 9:24
 */
public class ServletDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理表单提交的数据
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        //构造一个相应页面
        resp.setContentType("text/html; charset=utf-8");
        //一旦调用writer，header中的内容就无法修改，因此resp.setContentType需要先执行
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("firstName: "+firstName);
        writer.write("</br>");
        writer.write("secondName: "+secondName);
        writer.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
