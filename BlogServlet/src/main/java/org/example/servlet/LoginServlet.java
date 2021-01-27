package org.example.servlet;

import org.example.exception.AppException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-24 17:13
 */
@WebServlet("/login")
public class LoginServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("abc".equals(username)){//模拟用户名密码校验
           return null;
        }else {
            throw new AppException("LOG001","用户名或者密码错误");
        }
    }
}
