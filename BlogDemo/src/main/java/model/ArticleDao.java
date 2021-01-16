package model;

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
 * Date    2021-01-16 15:10
 */
public class ArticleDao {
    //1、新增文章
    public void add(Article article){
        //①获取数据库连接
        Connection connection = DBUtil.getConnection();
        //②拼装sql
        String sql = "insert into article values(null,?,?,?)";
        //③执行sql
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getContent());
            statement.setInt(3,article.getUserId());
            int ret = statement.executeUpdate();
            if (ret != 1){
                System.out.println("发表博客失败~");
                return;
            }
            System.out.println("发表博客成功~");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    // 2. 查看文章列表(把所有的文章信息都查出来(没必要查正文))
    public List<Article> selectAll(){
        List<Article> articles = new ArrayList<>();
        //①建立数据库连接
        Connection connection = DBUtil.getConnection();
        //②拼装sql语句
        String sql = "select articleId,title,userId from article";
        //③执行sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            //④查询结果集
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
            //⑤关闭资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    // 3. 查看指定文章详情. (需要查正文)
    public Article selectById(int articleId){
        //①建立数据库连接
        Connection connection = DBUtil.getConnection();
        //②拼装sql
        String sql = "select * from article where articleId = ?";
        //③执行sql
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,articleId);
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
            //⑤关闭资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    // 4. 删除指定文章(给定文章 id 来删除)
    public void delete(int articleId){
        //①获取数据库连接
        Connection connection = DBUtil.getConnection();
        //②拼装sql
        String sql = "delete from article where articleId = ?";
        //③执行sql
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,articleId);
            int ret = statement.executeUpdate();
            if (ret != 1){
                System.out.println("删除博客失败");
                return;
            }
            System.out.println("删除博客成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

   /* public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        *//*Article article = new Article();
        article.setTitle("StringBuffer");
        article.setContent("StringBuffer是可变类，线程安全");
        article.setUserId(1);
        articleDao.add(article);*//*
        List<Article> articles = articleDao.selectAll();
        System.out.println(articles);
        Article article = articleDao.selectById(2);
        System.out.println(article);
    }*/
}
