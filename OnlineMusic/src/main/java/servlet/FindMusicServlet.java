package servlet;

import Entity.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import service.MusicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-04 18:27
 */
@WebServlet("/findMusic")
public class FindMusicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        String musicName = req.getParameter("musicName");
        MusicService musicService = new MusicService();
        List<Music> musicList = new ArrayList<>();
        System.out.println(musicName);

        if(musicName != null && !musicName.trim().isEmpty()) {
            musicList = musicService.ifMusic(musicName);
            System.out.println(musicList);
        }else {
            musicList = musicService.findMusic();
            System.out.println(musicList);
        }

        Map<String,Object> message = new HashMap<>();
        message.put("msg",musicList);
        //将信息返回给前端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),musicList);

    }
}
