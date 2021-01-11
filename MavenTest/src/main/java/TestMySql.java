import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-11 21:17
 */
public class TestMySql {
    public static void main(String[] args) throws SQLException {
        //借助dataSource管理数据库连接
        String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "261919Zss";
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL(url);
        ((MysqlDataSource) dataSource).setUser(username);
        ((MysqlDataSource) dataSource).setPassword(password);
        Connection connection = dataSource.getConnection();
        String sql = "select * from staff";
        PreparedStatement statement = connection.prepareStatement(sql);
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
