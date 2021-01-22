package com.ssz.profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("")
public class HomeServlet extends HttpServlet {
    private int  count;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        count++;
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String name  = req.getParameter("name");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();
        String template = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>个人名片</title>\n" +
                "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>访问次数: :count:</div>\n"+
                "    <div>\n" +
                "        <h1 style=\"text-align: center\">:name:</h1>\n" +
                "        <p>大三学生</p>\n" +
                "    </div>\n" +
                "    <div class=\"hobby\">\n" +
                "        <h2>我的爱好</h2>\n" +
                "        <ul>\n" +
                "            <li id=\"sing\">听歌</li>\n" +
                "            <li id=\"play\">逛街</li>\n" +
                "            <li id=\"eat\"> 美食</li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "    <div class=\"experience\">\n" +
                "        <h2>我的学习经历</h2>\n" +
                "        <ol>\n" +
                "            <li class=\"school\">幼儿园</li>\n" +
                "            <li class=\"school\">小学</li>\n" +
                "            <li class=\"school\">初中</li>\n" +
                "            <li class=\"school\">高中</li>\n" +
                "            <li class=\"school\">大学</li>\n" +
                "        </ol>\n" +
                "    </div>\n" +
                "    <div class=\"target\">\n" +
                "        <h2>我的目标</h2>\n" +
                "        <ul>\n" +
                "            <li><a href=\"http://www.baidu.com\">有能力参加百度面试</a></li>\n" +
                "            <li><a href=\"http://www.sogou.com\">有机会参加搜搜面试</a></li>\n" +
                "        </ul>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        String content = template.replace(":name:",name).replace(":count:",String.valueOf(count));

        printWriter.println(content);
    }
}
