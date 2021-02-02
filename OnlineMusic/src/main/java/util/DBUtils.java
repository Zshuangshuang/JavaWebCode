package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-02 11:16
 */
public class DBUtils {
    private static String url = "jdbc:mysql://localhost:3306/music_server?characterEncoding=utf-8&useSSL=false";
    private static String username = "root";
    private static String password = "261919Zss";

    private static volatile DataSource dataSource;

    private static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (DBUtils.class){
                if (dataSource == null){
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(url);
                    ((MysqlDataSource)dataSource).setUser(username);
                    ((MysqlDataSource)dataSource).setPassword(password);
                }
            }
        }
        return dataSource;
    }
    public static Connection getConnection(){
        try {
          Connection connection = getDataSource().getConnection();
          return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接异常");
        }

    }
    public static void getClose(Connection connection, Statement statement,ResultSet resultSet){

          try {
              if (resultSet != null){
                  resultSet.close();
              }
              if (statement != null){
                  statement.close();
              }
              if (connection != null){
                  connection.close();
              }

          }catch (SQLException e){
              e.printStackTrace();
          }
    }
    public static void getClose(Connection connection, Statement statement){
        getClose(connection, statement,null);
    }
}
