package dao;

import Entity.User;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-02 13:41
 */
public class UserDao {
    public static void resister(User user){
        Connection connection = null;
        PreparedStatement statement = null;
        String username = user.getUsername();
        String password = user.getPassword();
        int age = user.getAge();
        String gender = user.getGender();
        String email = user.getEmail();
        try{
            String sql = "insert into user(username,password,age,gender,email) values (?,?,?,?,?)";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setInt(3,age);
            statement.setString(4,gender);
            statement.setString(5,email);
            int ret = statement.executeUpdate();
            if (ret == 1){
                System.out.println("插入用户成功!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement);
        }
    }
    public  User login(User loginUser){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            String sql = "select * from  user where username=? and password=?";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,loginUser.getUsername());
            statement.setString(2,loginUser.getPassword());
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getString("gender"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return null;
    }


}
