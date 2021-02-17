package servlet;

import Entity.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MusicDao;

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
 * Date    2021-02-05 14:19
 */
@WebServlet("/deleteSelMusicServlet")
public class DeleteSelMusicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        String[] strings = req.getParameterValues("id[]");
        //strings存放的是所有需要删除的id
        MusicDao musicDao = new MusicDao();
        Map<String,Object> message = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < strings.length; i++) {
            int id = Integer.parseInt(strings[i]);
            //先判断有没有要删除的歌曲
            Music music = musicDao.findMusicById(id);
            if (music == null){
                return;
            }

            int ret = musicDao.deleteByMusicId(id);

            if (ret == 1){
                File file = new File("E:\\JavaWebCode\\OnlineMusic\\src\\main\\webapp\\"+music.getUrl()+".mp3");
                //File file = new File("/opt/apache-tomcat-8.5.57/webapps/OnlineMusic/"+music.getUrl()+".mp3");
                if (file.delete()){
                    sum += ret;
                }else {
                    message.put("msg",false);
                    System.out.println("服务器删除失败");

                }

            }else {
                message.put("msg",false);
                System.out.println("数据库删除数据失败");

            }
        }
        if (sum == strings.length){
            message.put("msg",true);
            System.out.println("批量删除成功");
        }else {
            message.put("msg",false);
            System.out.println("批量删除数据失败");
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),message);

    }
}
