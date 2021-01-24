package org.example.servlet;

import org.example.exception.AppException;

import javax.servlet.ServletException;
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
 * Date    2021-01-24 16:57
 */
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/html; charset-utf-8");
            //session会话管理,除登录和注册界面不需要,其他都需要
            //通过req.getServletPath()获取请求服务路径和URL对比用来校验是否为登录状态
            //TODO
            //调用子类重写的方法
            process(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            String str = "服务器未知的错误";
            //自定义异常处理:包括JDBC异常,JSON序列化异常,账号和密码异常
            if (e instanceof AppException){
                str = e.getMessage();
            }
            PrintWriter printWriter = resp.getWriter();
            printWriter.println(str);
            printWriter.flush();
            printWriter.close();
        }
    }
    protected abstract void process(HttpServletRequest req,
                                    HttpServletResponse resp) throws Exception;
}
