package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:负责管理数据库连接
 * //1)建立连接
 * //2)断开连接
 * JDBC 中使用 DataSource 来管理连接.
 * DBUtil 相当于是对 DataSource 再稍微包装一层.
 * DataSource 每个应用程序只应该有一个实例(单例)
 * DBUtil 本质上就是实现了一个单例模式, 管理了唯一的一个 DataSource 实例
 * Date    2021-01-16 11:36
 */
public class DBUtil {
    //这里的volatile是为了防止内存可见性优化代码出现的线程安全问题
    private static volatile DataSource dataSource = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/blogdemo?characterEncoding=utf-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "261919Zss";

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    // 还需要给 DataSource 设置一些属性.
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;

    }
    //设置一个获取数据库连接的方法
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //设置一个断开数据库连接的方法
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
