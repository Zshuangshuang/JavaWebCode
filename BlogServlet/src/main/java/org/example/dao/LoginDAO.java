package org.example.dao;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-28 11:20
 */
public class LoginDAO {
    public static User query(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
       try {
            connection = DBUtil.getConnection();
            String sql = "select userId, username, password, nickName, sex, birthday, head " +
                    "from user where username=?";
            preparedStatement = connection.prepareStatement(sql);
            //设置占位符的值
           preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            while(resultSet.next()){
                user = new User();
                //设置User的值
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setHead(resultSet.getString("head"));
                user.setNickname(resultSet.getString("nickName"));
                user.setSex(resultSet.getString("sex"));
            }
            return user;
       }catch (Exception e){
            throw new AppException("LOG001","查询用户操作出错",e);
       }finally {
           DBUtil.close(connection,preparedStatement,resultSet);
       }
    }
}
