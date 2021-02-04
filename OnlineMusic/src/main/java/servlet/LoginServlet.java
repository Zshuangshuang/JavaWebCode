package servlet;

import Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-04 16:21
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+": "+password);

        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        UserService userService = new UserService();
        User user = userService.login(loginUser);

        Map<String,Object> message = new HashMap<>();


        if(user != null) {
            System.out.println("登录成功！");
            //将该用户的信息写入到session
            req.getSession().setAttribute("user", user);//绑定数据
            message.put("msg",true);
        }else {
            System.out.println("登录失败！");
            message.put("msg",false);
        }

        //将信息返回给前端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), message);

    }
}
