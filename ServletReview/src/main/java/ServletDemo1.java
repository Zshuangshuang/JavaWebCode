import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-14 10:40
 */
//这里要上传图片，必须加上@MultipartConfig注解，否则无法上传图片
@MultipartConfig
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //收到图片后，直接将图片保存到d盘中指定目录
        //1)先将保存路径的图片准备好
        String basePath = "d:/ServletReview/images";
        //此处得到的part对象，是上传文件的内容(响应头中有这个key,通过这个key查找到对应的文件)
        Part image = req.getPart("image");
        //得到上传的文件名
        String path = basePath+ image.getSubmittedFileName();
        image.write(path);
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("图片上传成功");
    }
}
