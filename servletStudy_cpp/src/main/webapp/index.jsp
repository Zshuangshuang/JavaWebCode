
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
private int count;
%>
<%
    count++;
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html; charset= utf-8");
    String name = request.getParameter("name");
    String count = request.getParameter("count");
%>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>个人名片</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div>
    <h1 style="text-align: center"> <%= name %> </h1>
    <p>大三学生</p>
</div>
<div class="hobby">
    <h2>我的爱好</h2>
    <ul>
        <li id="sing">听歌</li>
        <li id="play">逛街</li>
        <li id="eat"> 美食</li>
    </ul>
</div>
<div class="experience">
    <h2>我的学习经历</h2>
    <ol>
        <li class="school">幼儿园</li>
        <li class="school">小学</li>
        <li class="school">初中</li>
        <li class="school">高中</li>
        <li class="school">大学</li>
    </ol>
</div>
<div class="target">
    <h2>我的目标</h2>
    <ul>
        <li><a href="http://www.baidu.com">有能力参加百度面试</a></li>
        <li><a href="http://www.sogou.com">有机会参加搜搜面试</a></li>
    </ul>
</div>
</body>
</html>