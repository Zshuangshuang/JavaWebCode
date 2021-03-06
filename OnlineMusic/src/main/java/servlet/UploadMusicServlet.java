package servlet;

import Entity.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Description:上传音乐到服务器目录
 */
@WebServlet("/upload")
public class UploadMusicServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        String realPath = config.getServletContext().getRealPath("/music");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //上传
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = null;

        try {
            fileItems = upload.parseRequest(req);
        } catch (FileUploadException e) {
            throw new ServletException(e);

        }

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("请登录后在上传音乐");
            resp.sendRedirect(resp.encodeRedirectURL("login.html"));
        } else {
            System.out.println("fileItems: " + fileItems);
            FileItem fileItem = fileItems.get(0);
            System.out.println("fileItem: " + fileItem);

            String fileName = fileItem.getName();
            req.getSession().setAttribute("fileName", fileName);
            try {
                String realPath = req.getServletContext().getRealPath("/music");
                File file = new File(realPath, fileName);
                if (file.exists()) {
                    file.delete();
                }
                fileItem.write(file);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            //将文件上传到数据库
            resp.sendRedirect("uploadsucess.html");

        }
    }
}