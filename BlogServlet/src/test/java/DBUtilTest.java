import org.example.util.DBUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-24 17:45
 */
public class DBUtilTest {
    @Test
    public void test(){
        Connection connection = DBUtil.getConnection();
        Assert.assertNotNull(connection);
    }
}
