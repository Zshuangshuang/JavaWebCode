package modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-14 16:20
 */
public class UserDao {
    //①新增用户(注册的时候涉及)
  public void addUser(User user){
        //1)获取数据库连接
        Connection connection = DBUtil.getConnection();
        //2)拼装sql语句
        String sql = "insert into user values (null,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
            //3)执行sql语句
            int ret = statement.executeUpdate();
            if (ret != 1){
                System.out.println("插入新用户失败~");
                return;
            }else {
                System.out.println("插入新用户成功~");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4)释放数据库连接
            DBUtil.close(connection,statement,null);
        }

    }
    //②按名字查找用户(用户登录)
    public User selectByName(String name){
        User user = null;
        //1)和数据库建立连接
        Connection connection = DBUtil.getConnection();
        //2)拼装sql语句
        String sql = "select * from user where name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            //3)执行sql语句
            resultSet = statement.executeQuery();
            //4)遍历结果集(由于name在数据库中是unique的，因此这里用if)
            if (resultSet.next()){
               user = new User();
               user.setUserId(resultSet.getInt("userId"));
               user.setName(resultSet.getString("name"));
               user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //5)释放连接
            DBUtil.close(connection,statement,resultSet);
        }
      return null;
    }

   /* public static void main(String[] args) {
      //1)测试UserDao的新增用户功能
       *//* UserDao userDao = new UserDao();
        User user = new User();
        user.setName("zss");
        user.setPassword("123");
        userDao.addUser(user);*//*
        //2)测试UserDao的按照姓名查找人的功能
        *//*UserDao userDao = new UserDao();
        User user = userDao.selectByName("zss");
        System.out.println(user);*//*
    }*/
}
