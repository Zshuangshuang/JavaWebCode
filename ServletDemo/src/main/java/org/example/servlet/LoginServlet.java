package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-18 20:10
 */
//注解的使用：小括号包裹多个属性(),属性名=属性值，多个之间用逗号间隔
//Servlet定义服务，服务路径以/开头，否则会报错
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求，响应编码以及响应的数据类型
        req.setCharacterEncoding("UTF-8");//设置请求体编码
        resp.setCharacterEncoding("UTF-8");//设置响应体编码
        resp.setContentType("text/html");

        //解析请求数据
        String userName = req.getParameter("username");
        String pWord = req.getParameter("password");
        System.out.printf("=========================用户名 (%s) 密码 (%s) %n",userName,pWord);
        if("abc".equals(userName) && "123".equals(pWord)){
            //重定向:实现页面跳转
            resp.sendRedirect("home.html");

        }else if ("abc".equals(userName)){
            //转发
            req.getRequestDispatcher("home.html").forward(req,resp);
        }
        else {
            //响应内容
            PrintWriter pw = resp.getWriter();//response IO输出流
            pw.write("登陆失败");
            pw.write("<h3>用户名或者密码错误</h3>");
            pw.flush();
            pw.close();
        }


    }
}
