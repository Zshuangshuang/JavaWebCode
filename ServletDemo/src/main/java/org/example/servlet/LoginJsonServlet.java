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
@WebServlet("/loginJson")
public class LoginJsonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求，响应编码以及响应的数据类型
        req.setCharacterEncoding("UTF-8");//设置请求体编码
        resp.setCharacterEncoding("UTF-8");//设置响应体编码
        resp.setContentType("application/json");

        //解析请求数据
        String userName = req.getParameter("username");
        String pWord = req.getParameter("password");
        System.out.printf("=========================用户名 (%s) 密码 (%s) %n",userName,pWord);

            //响应内容
            PrintWriter pw = resp.getWriter();//response IO输出流
            pw.write("{\"success\": true}");
            pw.flush();
            pw.close();
        }


    }

