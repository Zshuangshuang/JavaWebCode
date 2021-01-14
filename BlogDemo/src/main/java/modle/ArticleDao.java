package modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-14 16:46
 */
public class ArticleDao {
    //①新增文章(发布博客)
    public void addArticle(Article article){
        //1)获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2)拼装sql语句
        String sql = "insert into article values(null,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getContent());
            statement.setInt(3,article.getUserId());
            //3)执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1){
                System.out.println("插入文章失败");
                return;
            }else {
                System.out.println("插入文章成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4)释放连接
            DBUtil.close(connection,statement,null);
        }

    }
    //②查看文章列表(把所有文章信息都查出来[不查看正文])
    public List<Article> selectAll(){
        List<Article> articles = new ArrayList<>();
        //1)建立数据库连接
        Connection connection = DBUtil.getConnection();
        //2)构造sql语句
        String sql = "select articleId,title,userId from article";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //3)执行sql
            statement = connection.prepareStatement(sql);
            //4)遍历结果集
        resultSet = statement.executeQuery();
        while(resultSet.next()){
           Article article = new Article();
           article.setArticleId(resultSet.getInt("articleId"));
           article.setTitle(resultSet.getString("title"));
           article.setUserId(resultSet.getInt("userId"));
            articles.add(article);
        }
        return articles;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5)释放连接
            DBUtil.close(connection,statement,resultSet);
        }

       return null;
    }
    //③查看指定文章详情(需要查看正文)
    public Article selectById(int articleId){
        //①建立连接
        Connection connection = DBUtil.getConnection();
        //②建立sql
        String sql = "select * from article where articleId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,articleId);
            //③执行sql
            resultSet = statement.executeQuery();
            //④遍历结果集
            if (resultSet.next()){
                Article article = new Article();
                article.setArticleId(resultSet.getInt("articleId"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(resultSet.getInt("userId"));
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //⑤释放连接
            DBUtil.close(connection,statement,resultSet);
        }

       return null;
    }
    //④删除博客(根据文章Id删除)
    public void delete(int articleId){
        //①建立连接
        Connection connection = DBUtil.getConnection();
        //②创建sql语句
        String sql = "delete from article where articleId = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,articleId);
            //③执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1){
                System.out.println("删除博客失败~");
                return;
            }else {
                System.out.println("博客已删除");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //④释放连接
            DBUtil.close(connection,statement,null);
        }
    }

   /* public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        //1、测试ArticleDao的添加文章功能
       *//* Article article = new Article();
        article.setTitle("Java语言");
        article.setContent("Java is good");
        article.setUserId(1);
        article.setTitle("C语言");
        article.setContent("指针很难");
        article.setUserId(1);
        articleDao.addArticle(article);*//*
        //2、测试查看文章列表
       *//* List<Article> articles = articleDao.selectAll();
        System.out.println(articles);*//*
        //3、查看指定文章的内容
        *//*Article article = articleDao.selectById(1);
        System.out.println(article);*//*
        //4、删除文章
        articleDao.delete(2);
    }*/
}
