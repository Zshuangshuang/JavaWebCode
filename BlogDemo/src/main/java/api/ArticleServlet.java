package api;

import model.Article;
import model.ArticleDao;
import model.User;
import model.UserDao;
import view.HtmlGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-16 20:25
 */
public class ArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=ytf-8");
        //①验证用户是否为登录状态，日过没有登录，就提醒用户要登录
        HttpSession httpSession = req.getSession(false);
        if (httpSession == null){
            String html = HtmlGenerator.getMessagePage("用户尚未登录","login.html");
            resp.getWriter().write(html);
            return;
        }
        User user = (User) httpSession.getAttribute("user");
        //②判断请求中是否存在 articleId 参数.
        String articleIdStr = req.getParameter("articleId");
        //a) 没有这个参数就去执行获取文章列表操作
        if (articleIdStr == null){
            getAllArticle(user, resp);
        }else {
            //b)  有这个参数就去执行获取文章详情操作.
            getOneArticle(Integer.parseInt(articleIdStr), user, resp);
        }

    }

    //获取所有文章
    private void getAllArticle(User user, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1、在数据库中查找
        ArticleDao articleDao = new ArticleDao();
        List<Article> articles = articleDao.selectAll();
        //2、构造页面
        String html = HtmlGenerator.getArticleListPage(articles, user);
        resp.getWriter().write(html);
    }
    //获取文章详情
    private void getOneArticle(int articleId, User user, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=utf-8");
        //①查找数据库
        ArticleDao articleDao = new ArticleDao();
        Article article = articleDao.selectById(articleId);
        if (article == null){
            String html = HtmlGenerator.getMessagePage("文章不存在","article.html");
            resp.getWriter().write(html);
            return;
        }
        /*//为了验证换行消失问题，将article中的正文打印出来
        System.out.println("文章内容:"+article.getContent());*/
        //②根据作者id，找到作者信息
        UserDao userDao = new UserDao();
        User author = userDao.selectByUserId(article.getUserId());
        //③构造页面
        String html = HtmlGenerator.getArticleDetailPage(article,user,author);
        resp.getWriter().write(html);
    }

    @Override
    //负责发布文章
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=utf-8");
        //①判断用户登录状态，若用户尚未登录，则需要提示用于登录
        HttpSession httpSession = req.getSession(false);
        if(httpSession == null){
            String html = HtmlGenerator.getMessagePage("用户未登录","login.html");
            resp.getWriter().write(html);
            return;
        }
        User user = (User) httpSession.getAttribute("user");
        //②从请求中读取浏览器提交的参数(title,content)并进行校验
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || "".equals(title) || content == null || "".equals(content)){
            String html = HtmlGenerator.getMessagePage("提交的标题或者正文为空","article");
            resp.getWriter().write(html);
            return;
        }
        //③将数据插入数据库
        ArticleDao articleDao = new ArticleDao();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(user.getUserId());
        articleDao.add(article);
        //④返回发布成功页面
        String html = HtmlGenerator.getMessagePage("发布成功","article");
        resp.getWriter().write(html);
    }
}
