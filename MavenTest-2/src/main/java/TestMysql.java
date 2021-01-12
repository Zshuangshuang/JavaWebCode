import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-12 9:07
 */
public class TestMysql {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "261919Zss";
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL(url);
        ((MysqlDataSource)dataSource).setUser(username);
        ((MysqlDataSource)dataSource).setPassword(password);
        //1)建立数据库连接
        Connection connection = dataSource.getConnection();
        //2)创建sql语句
        String sql = "select * from staff";
        //3)执行sql语句
        PreparedStatement statement = connection.prepareStatement(sql);
        //4)遍历结果集
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getInt("age"));
            System.out.println(resultSet.getBigDecimal("salary"));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
