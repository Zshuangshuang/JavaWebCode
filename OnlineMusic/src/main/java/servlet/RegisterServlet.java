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


@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置前端的响应格式
        resp.setContentType("application/json; charset=utf-8");
        //获取到前端提交的数据(获取用户名和密码)，并校验用户名密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        Map<String,Object> message = new HashMap<>();
        //根据用户名在数据库中查找，如果存在，则注册失败

        UserService userService = new UserService();
        User exitsUser =userService.selectByName(username);
        if (exitsUser == null){
            //根据用户提交的数据，构造user数据，插入数据库
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setAge(Integer.parseInt(age));
            user.setGender(gender);
            user.setEmail(email);
            userService.register(user);
            req.getSession().setAttribute("user", user);//绑定数据
            message.put("msg",true);

        }else {
            message.put("msg",false);
        }
        //将信息返回给前端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), message);
    }
}
