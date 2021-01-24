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
    public void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("abc".equals(username)){
            resp.sendRedirect("jsp/articleList.jsp");
        }else {
            throw new AppException("用户名密码错误");
        }
    }
}
