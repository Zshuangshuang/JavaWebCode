package servlet;

import Entity.Music;
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
import java.util.List;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-05 14:57
 */
@WebServlet("/findLoveMusic")
public class FindLoveMusicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");


        String loveMusicName = req.getParameter("loveMusicName");
        MusicService musicService = new MusicService();
        User user = (User) req.getSession().getAttribute("user");
        int user_id = user.getId();

        List<Music> musicList;
        if(loveMusicName != null) {
            musicList = musicService.ifMusicLove(loveMusicName,user_id);
        }else {
            musicList = musicService.findLoveMusic(user_id);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),musicList);
    }
}
