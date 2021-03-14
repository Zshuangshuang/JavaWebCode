package servlet;

import Entity.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;
import service.MusicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-05 11:58
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        String idStr  = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        MusicService musicService = new MusicService();
        //判断当前id的音乐是否存在
        Music music = musicService.findMusicById(id);
        if (music == null){
          return;
        }
        int ret = musicService.deleteByMusicId(id);
        Map<String,Object> message = new HashMap<>();
        if (ret == 1){
            //数据库里面的音乐信息已经删除,接下来需要删除服务器上面的音乐
            //windows上的music路径
            //File file = new File("E:\\JavaWebCode\\OnlineMusic\\src\\main\\webapp\\"+music.getUrl()+".mp3");
           File file = new File("/opt/apache-tomcat-8.5.57/webapps/OnlineMusic/"+music.getUrl()+".mp3");
            if (file.delete()){
                message.put("msg",true);
                System.out.println("服务器删除成功~");
            }else {
                message.put("msg",false);
                System.out.println("服务器删除失败~");
            }
        }
        //将响应信息写回前端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),message);
    }
}
