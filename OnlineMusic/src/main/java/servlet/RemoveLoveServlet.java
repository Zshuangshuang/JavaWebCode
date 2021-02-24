package servlet;

import Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import service.MusicService;

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
 * Date    2021-02-05 15:13
 */
@WebServlet("/removeLoveServlet")
public class RemoveLoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");

        String idStr = req.getParameter("id");
        int musicId = Integer.parseInt(idStr);
        User user = (User)req.getSession().getAttribute("user");
        int userId = user.getId();
        Map<String,Object> message = new HashMap<>();
        MusicService musicService = new MusicService();
        int ret = musicService.removeLoveMusic(userId,musicId);
        if (ret == 1){
            message.put("msg",true);
        }else {
            message.put("msg",false);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),message);
    }
}
