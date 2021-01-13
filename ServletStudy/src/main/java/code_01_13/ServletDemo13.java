package code_01_13;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 16:02
 */
public class ServletDemo13 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html; charset=utf-8");
       Writer writer = resp.getWriter();
       writer.write("<html>");
       Enumeration<String> headerNames = req.getHeaderNames();
        //遍历http请求中的头部信息
        while(headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            writer.write(header+": "+req.getHeader(header));
            writer.write("<br>");
        }
        //这种类似于迭代器
       writer.write("</html>");

    }
}
