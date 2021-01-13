package code_01_13;

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
 * Date    2021-01-13 19:13
 */
public class ServletDemo17 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1)实例化两个cookie对象
        Cookie cookie1 = new Cookie("name","Zss");
        Cookie cookie2 = new Cookie("age","20");
        //2)向响应中添加cookie
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
        //3)指定cookie绑定的路径,这里必须要带上项目名称，这里是将cookie写入浏览器的缓存
        cookie1.setPath(req.getContextPath()+"/aaa");
        cookie2.setPath(req.getContextPath()+"/bbb");
        //4)设置cookie的有效期，这里如果该值大于0是将cookie写入客户的磁盘，小于0与不设置是相同的，都是写入磁盘
        //设置的值如果为0，那么久相当于cookie刚生成就失效了
        cookie1.setMaxAge(60*60*24*10);//相当于有效期为10天
        //5)设置响应报文
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<h1>响应成功</h1>");
    }
}
