package http.practice.www;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-08 9:12
 */
public class HttpSeverV2 {
    private ServerSocket serverSocket = null;

    public HttpSeverV2(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    process(clientSocket);
                }
            });
        }
    }
    public void process(Socket clientSocket) {
        try {
            HttpRequest request = HttpRequest.build(clientSocket.getInputStream());
            System.out.println("request:"+request);
            HttpResponse response = HttpResponse.build(clientSocket.getOutputStream());
            response.setHeaders("Content-Type","text/html");
            //根据请求计算响应
            if ("/hello".equals(request.getUrl())){
                response.setStatus(200);
                response.setMessage("OK");
                response.writeBody("<h1>hello</h1>");
            }else if ("/cookieTime".equals(request.getUrl())){
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeaders("Set-Cookie","Time = "+System.currentTimeMillis()/1000);
                response.writeBody("<h1>cookieTime</h1");
            }else if ("/cookieName".equals(request.getUrl())){
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeaders("Set-Cookie","UserName =zs ");
                response.writeBody("<h1>cookieUser</h1>");
            }else {
                response.setStatus(200);
                response.setMessage("OK");
                response.writeBody("<h1>default</h1>");
            }
            response.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
       HttpSeverV2 sever = new HttpSeverV2(9090);
       sever.start();
    }
}
