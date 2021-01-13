package code_01_13;

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
 * Date    2021-01-13 15:37
 */
public class ServletDemo11 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String encoding = req.getCharacterEncoding();
        String contentType = req.getContentType();
        String contentPath = req.getContextPath();
        String user = req.getHeader("User-Agent");
        String host = req.getHeader("Host");
        String method = req.getMethod();
        String protocol = req.getProtocol();
        String url = req.getRequestURI();
        int contentLength = req.getContentLength();
        Writer writer = resp.getWriter();
        writer.write("<html>");
        writer.write("encoding: "+encoding);
        writer.write("</br>");
        writer.write("Content-Type: "+contentType);
        writer.write("</br>");
        writer.write("Content-Path: "+contentPath);
        writer.write("</br>");
        writer.write("User-Agent: "+user);
        writer.write("</br>");
        writer.write("Host: "+host);
        writer.write("</br>");
        writer.write("Method: "+method);
        writer.write("</br>");
        writer.write("Protocol: "+protocol);
        writer.write("</br>");
        writer.write("Url: "+url);
        writer.write("</br>");
        writer.write("Content-Length: "+contentLength);
        writer.write("</br>");
        writer.write("</html>");
    }
}
