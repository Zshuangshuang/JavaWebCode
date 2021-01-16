package api;

import model.User;
import model.UserDao;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-16 19:49
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //①获取用户名和密码，并校验
        String name  = req.getParameter("name");
        String password = req.getParameter("password");
        if (name == null || "".equals(name) || password == null || "".equals(password)){
            String html = HtmlGenerator.getMessagePage("用户名或者密码为空","login.html");
            resp.getWriter().write(html);
            return;
        }
        //②在数据库中查找，看用户是否存在
        //③比对用户名和密码，看是否匹配
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(name);
        if (user == null || !user.getPassword().equals(password)){
            String html = HtmlGenerator.getMessagePage("用户名或者密码错误","login.html");
            resp.getWriter().write(html);
            return;
        }
        //④匹配成功，则登录成功；创建一个session
        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("user",user);
        //⑤返回一个登录成功的页面
        String html = HtmlGenerator.getMessagePage("登录成功","article");
        resp.getWriter().write(html);
    }
}
