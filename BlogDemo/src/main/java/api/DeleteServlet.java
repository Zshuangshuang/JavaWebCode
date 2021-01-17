package api;

import model.Article;
import model.ArticleDao;
import model.User;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-17 10:14
 */
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1.先验证用户登录状态，如果尚未登录，则不能删除
        HttpSession httpSession = req.getSession(false);
        if (httpSession == null){
            String html = HtmlGenerator.getMessagePage("用户尚未登录","login.html");
            resp.getWriter().write(html);
            return;
        }
        User user =(User) httpSession.getAttribute("user");
        //2.读取请求内容，获取要删除文章的文章id
        String articleIdStr = req.getParameter("articleId");
        if (articleIdStr == null ||"".equals(articleIdStr)){
            String html = HtmlGenerator.getMessagePage("当前文章Id错误","article");
            resp.getWriter().write(html);
            return;
        }
        //3、根据文章id，找到文章作者,如果当前用户就是作者，才能成功删除文章,否则删除失败
        ArticleDao articleDao = new ArticleDao();
        Article article = articleDao.selectById(Integer.parseInt(articleIdStr));
        if (article.getUserId() != user.getUserId()){
            String html = HtmlGenerator.getMessagePage("你只能删除自己的文章","article");
            resp.getWriter().write(html);
            return;
        }
        //4.执行删除操作
        articleDao.delete(Integer.parseInt(articleIdStr));
        //5.返回一个删除成功的页面
        String html = HtmlGenerator.getMessagePage("删除成功","article");
        resp.getWriter().write(html);
    }
}
