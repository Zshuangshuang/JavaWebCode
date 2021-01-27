package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-24 17:24
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/servlet_blog?characterEncoding=utf-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "261919Zss";

    private static final DataSource DS = new MysqlDataSource();
    static {
        ((MysqlDataSource)DS).setURL(URL);
        ((MysqlDataSource)DS).setUser(USERNAME);
        ((MysqlDataSource)DS).setPassword(PASSWORD);
    }
    public static Connection getConnection(){
        try {
            return DS.getConnection();
        } catch (SQLException e) {
           //实现自定义异常
           throw new AppException("DB001","获取数据库连接失败",e);
        }

    }
}
