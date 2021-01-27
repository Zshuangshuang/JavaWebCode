import org.example.util.DBUtil;
import org.junit.Assert;

import java.sql.Connection;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-24 17:45
 */
public class DBUtilTest {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        Assert.assertNotNull(connection);
    }
}
