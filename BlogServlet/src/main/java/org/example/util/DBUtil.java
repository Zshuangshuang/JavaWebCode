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
    private static final String USER = "root";
    private static final String PASSWORD = "261919Zss";
    private static final DataSource dataSource = new MysqlDataSource();
    static{
        ((MysqlDataSource)dataSource).setURL(URL);
        ((MysqlDataSource)dataSource).setUser(USER);
        ((MysqlDataSource)dataSource).setPassword(PASSWORD);
    }
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {

            throw new AppException("获取数据库连接失败",e);
        }

    }
}
