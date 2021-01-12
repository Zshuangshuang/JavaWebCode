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
 * Date    2021-01-12 16:53
 */
public class TestMysql {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "261919Zss";
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource)dataSource).setURL(url);
        ((MysqlDataSource)dataSource).setUser(username);
        ((MysqlDataSource) dataSource).setPassword(password);
        Connection connection = dataSource.getConnection();
        String sql = "select * from staff";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String sex = resultSet.getString("sex");
            int age = resultSet.getInt("age");
            BigDecimal salary = resultSet.getBigDecimal("salary");
            System.out.println("id="+id+"name="+name+"sex="+sex+"age="+age+"salary="+salary);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
