package org.example.servlet;

import org.example.dao.LoginDAO;
import org.example.exception.AppException;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        User user = LoginDAO.query(username);
        if (user == null){
            throw new AppException("LOG002","用户不存在");
        }
        if (!user.getPassword().equals(password)){
            throw new AppException("LOG003","用户或者密码错误");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user",user);
        return null;
        /*if ("abc".equals(username)){//模拟用户名密码校验
           return null;
        }else {
            throw new AppException("LOG001","用户名或者密码错误");
        }*/
    }
}
