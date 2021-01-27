package org.example.servlet;

import org.example.exception.AppException;
import org.example.model.JsonResponse;
import org.example.util.JSONUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-24 16:57
 */
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求体体编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应体编码格式
        resp.setCharacterEncoding("utf-8");
        //设置响应体的数据类型
        resp.setContentType("application/json; charset=utf-8");
        //会话管理：除了登录和注册接口，其他都要登录后才能访问
        //通过req.getServletPath()获取请求服务路径
        JsonResponse json = new JsonResponse();
        try {

            //调用子类重写的方法
            Object data = process(req, resp);
            //一下标识子类Process方法执行完毕,没有抛出异常
            json.setSuccess(true);
            json.setData(data);
        }catch (Exception e){
            //处理异常，包括JSON异常，JDBC异常
            e.printStackTrace();
            String code = "Unknown";
            String s = "服务器未知错误";
            if (e instanceof AppException){
                code = ((AppException)e).getCode();
                s = e.getMessage();
            }
            json.setCode(code);
            json.setMessage(s);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(JSONUtil.serialize(json));
        pw.flush();
        pw.close();
    }

    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}

