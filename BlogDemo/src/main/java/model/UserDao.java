package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:实现数据库中用户的增删查改操作
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-16 11:50
 */
public class UserDao {
    //1、新增用户
    public void add(User user){
        // 1. 获取到数据库连接.
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL 语句
        String sql = "insert into user values (null, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            // 3. 执行 SQL 语句
            int ret = statement.executeUpdate();
            if (ret != 1) {
                System.out.println("插入新用户失败!");
                return;
            }
            System.out.println("插入新用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 释放数据库的连接.
            DBUtil.close(connection, statement, null);

        }
    }
    //2、登录的时候按名字查找用户
    public User selectByName(String name){
        //①建立数据库连接
        Connection connection = DBUtil.getConnection();
        //②拼装sql语句
        String sql = "select * from user where name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //③实现sql语句
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            //④遍历结果集
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //⑤释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //3、根据用户id找到用户信息
    public User selectByUserId(int userId){
        //①和数据库建立连接
        Connection connection =DBUtil.getConnection();
        //②拼装sql语句
        String sql = "select * from user where userId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //③实现sql
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            //④遍历结果集
            if (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //⑤释放资源
            DBUtil.close(connection,statement,resultSet);
        }
        return null;

    }
    //测试以上功能
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        /*User user1 = new User();
        user1.setName("zss");
        user1.setPassword("123");
        userDao.add(user1);
        User user2 = new User();
        user2.setName("xln");
        user2.setPassword("999");
        userDao.add(user2);*/
        /*User user1 = userDao.selectByName("zss");
        User user1 = userDao.selectByUserId(1);
        System.out.println(user1);*/
    }
}
