package view;

import model.Article;
import model.User;

import java.util.List;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-16 19:11
 */
public class HtmlGenerator {
    public static String getMessagePage(String message,String nextUrl){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"utf-8\">");
        stringBuilder.append("<title>提示页面</title>");
        stringBuilder.append("<style type=\"text/css\">body{" +
                "background-image: url(\"https://tse1-mm.cn.bing.net/th/id/OIP.w-P7oEX8QhMGijkVDpA5NwHaEK?pid=Api&rs=1\");" +
                "background-repeat: no-repeat;"+
                "background-size: 100%;"+
                "}");
        stringBuilder.append("</style>");
        stringBuilder.append("<body>");
        stringBuilder.append("<h2>");
        stringBuilder.append(message);
        stringBuilder.append("</h2>");

        stringBuilder.append(String.format("<a href=\"%s\">点击这里跳转<a>",nextUrl));

        stringBuilder.append("</body>");
        stringBuilder.append("</head>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
public static String getArticleListPage(List<Article> articles, User user){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<html>");
    stringBuilder.append("<head>");
    stringBuilder.append("<meta charset=\"utf-8\">");
    stringBuilder.append("<title>提示页面</title>");
    //书写css
    stringBuilder.append("<style type=\"text/css\">body{" +
            "background-image: url(\"https://tse1-mm.cn.bing.net/th/id/OIP.w-P7oEX8QhMGijkVDpA5NwHaEK?pid=Api&rs=1\");" +
            "background-repeat: no-repeat;"+
            "background-size: 100%;"+
            "}");
    stringBuilder.append("</style>");
    stringBuilder.append("<style>");
    stringBuilder.append(".article{" +
            "color: #FFFAFA"+
            "text-decoration: none;"+
            //"display: inline-block;"+
            "width: 200px;"+
            "height: 50px;"+
            "}");
    stringBuilder.append(".article:hover{" +
            "color:Lavende;"+
            "background: LightSkyBlue;"+
            "}");
    stringBuilder.append("</style>");
    stringBuilder.append("<body>");
    stringBuilder.append("<h3>欢迎你"+user.getName()+"</h3>");
    stringBuilder.append("<hr>");
    //文章列表,显示每个文章的标题
    for (Article article : articles){
        stringBuilder.append(String.format("<div style=\"width: 200px; height: 50px; line-height: 50px\">" +
                        "<a class=\"article\" href=\"article?articleId=%d\">%s</a>" +
                        "<a href=\"deleteArticle?articleId=%d\"> 删除文章 </a>"+
                        "</div>"
                ,article.getArticleId(),article.getTitle(),article.getArticleId()));
    }
    stringBuilder.append("<hr>");
    stringBuilder.append(String.format("<div>当前共有博客%d篇</div>",articles.size()));
    //处理发布文章
    stringBuilder.append("<div>发布文章</div>");
    stringBuilder.append("<div>");
    stringBuilder.append("<form method=\"post\" action=\"article\">");
    stringBuilder.append("<input type=\"text\" style=\"width: 500px; margin-bottom: 5px; \"name=\"title\" placeholder=\"请输入标题\">");
    stringBuilder.append("<br>");
    stringBuilder.append("<textarea name=\"content\" style=\"width: 500px; height: 300px;\"></textarea>");
    stringBuilder.append("<br>");
    stringBuilder.append("<input type=\"submit\" value-\"发布文章\">");
    stringBuilder.append("</form>");
    stringBuilder.append("</div>");
    stringBuilder.append("</body>");
    stringBuilder.append("</head>");
    stringBuilder.append("</html>");
    return stringBuilder.toString();

}
public static String getArticleDetailPage(Article article,User user,User author){
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("<html>");
    stringBuilder.append("<head>");
    stringBuilder.append("<meta charset=\"utf-8\">");
    stringBuilder.append("<title>提示页面</title>");
    //书写css
    stringBuilder.append("<style type=\"text/css\">body{" +
            "background-image: url(\"https://tse1-mm.cn.bing.net/th/id/OIP.w-P7oEX8QhMGijkVDpA5NwHaEK?pid=Api&rs=1\");" +
            "background-repeat: no-repeat;"+
            "background-size: 100%;"+
            "}");
    stringBuilder.append("</style>");
    stringBuilder.append("<style>");
    stringBuilder.append("a{" +
            "color: #FFFAFA"+
            "text-decoration: none;"+
            "display: inline-block;"+
            "width: 200px;"+
            "height: 50px;"+
            "}");
    stringBuilder.append("a:hover{" +
            "color:Lavende;"+
            "background: LightSkyBlue;"+
            "}");
    stringBuilder.append("</style>");
    stringBuilder.append("<body>");
    stringBuilder.append("<h3>欢迎你"+user.getName()+"</h3>");
    stringBuilder.append("<hr>");
    stringBuilder.append(String.format("<h2>%s</h2>",article.getTitle()));
    stringBuilder.append(String.format("<h4>作者：%s</h4>",author.getName()));
    //构造正文内容
    stringBuilder.append("<div>文章内容如下:</div>");
    stringBuilder.append(String.format("<div>%s</div>",article.getContent().replace("\n","<br>")));

    stringBuilder.append("</body>");
    stringBuilder.append("</head>");
    stringBuilder.append("</html>");
    return stringBuilder.toString();

}

}
