package api;

import model.User;
import model.UserDao;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-16 19:04
 */
public class RegisterServlet extends HttpServlet {
    //浏览器通过doPost方法提交数据
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1、获取到前端提交的数据(获取用户名和密码)，并校验用户名密码
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (name == null || "".equals(name) || password == null
        || "".equals(password)){
            //用户提交的数据有问题，返回一个错误页面
            String html = HtmlGenerator.getMessagePage("用户名或者密码为空","register.html");
            resp.getWriter().write(html);
            return;
        }
        //2、根据用户名在数据库中查找，如果存在，则注册失败
        UserDao userDao = new UserDao();
        User exitsUser = userDao.selectByName(name);
        if (exitsUser != null){
            String html = HtmlGenerator.getMessagePage("该用户已存在，请重新注册","register.html");
            resp.getWriter().write(html);
            return;
        }
        //3、根据用户提交的数据，构造user数据，插入数据库
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.add(user);
        //4、返回一个注册成功的页面
        String html = HtmlGenerator.getMessagePage("注册成功!点击跳转至登录页面",
                "login.html");
        resp.getWriter().write(html);
    }
}
