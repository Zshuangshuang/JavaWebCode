package servlet;

import Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Date    2021-02-05 14:45
 */
@WebServlet("/loveMusicServlet")
public class LoveMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");

        String idStr = req.getParameter("id");
        int musicId = Integer.parseInt(idStr);

        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        MusicService musicService = new MusicService();
        // 判断以前这首歌是否被添加过为喜欢
        boolean effect = musicService.findMusicByMusicId(userId,musicId);

        Map<String ,Object> message = new HashMap<>();

        if(effect) {
            //以前这首歌被添加过为喜欢
            message.put("msg",false);
        }else {
            boolean flg = musicService.insertLoveMusic(userId,musicId);
            if(flg) {
                message.put("msg",true);
            }else {
                message.put("msg",false);
            }
        }
        //将message返回给前端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), message);

    }
}
