package code_01_13;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:返回一个错误页面
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-13 16:16
 */
public class ServletDemo15 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(403,"禁止访问");
    }
}
