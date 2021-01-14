package modle;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:用于管理和连接数据库
 * 1)建立连接
 * 2)断开连接
 * JDBC中使用DataSource来管理连接
 * DBUtil相当于实现单例模式来管理DataSource
 * 这里使用懒汉模式
 * Date    2021-01-14 14:08
 */
public class DBUtil {
    private static volatile DataSource dataSource = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/blogdemo?characterEncoding=utf-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "261919Zss";
    public static DataSource getDataSource(){
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null){
                    dataSource = new MysqlDataSource();
                    //设置数据库属性
                    ((MysqlDataSource) dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }
   //获取连接
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //断开连接，关闭资源
    public static void close(Connection connection, PreparedStatement statement,
                             ResultSet resultSet) {
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


