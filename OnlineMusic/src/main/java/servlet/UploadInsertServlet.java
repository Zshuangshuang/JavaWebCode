package servlet;

import Entity.User;
import dao.MusicDao;
import service.MusicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**

 * Description:将音乐数据写入数据库

 */
@WebServlet("/uploadsucess")
public class UploadInsertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset = utf-8");
        String singer = req.getParameter("singer");
        String fileName =(String) req.getSession().getAttribute("fileName");
        String[] strings = fileName.split("\\.");
        String title = strings[0];

        Date time = Date.from(Instant.now());
        User uer = (User)req.getSession().getAttribute("user");
        int userId = uer.getId();
        String url = "music/"+title;
        MusicService musicService = new MusicService();
        int ret = musicService.insert(title,singer,time,url,userId);
        if (ret == 1){
            resp.sendRedirect("list.html");
        }
    }
}
