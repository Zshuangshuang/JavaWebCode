package src.libs.code;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 20:29
 */
public class HttpServerVersion2 {
    private ServerSocket serverSocket = null;

    public HttpServerVersion2(int port) throws IOException {
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
    private void process(Socket clientSocket)  {
        try {
            //读取请求并解析
            HttpRequest httpRequest = HttpRequest.build(clientSocket.getInputStream());
            HttpResponse httpResponse = HttpResponse.build(clientSocket.getOutputStream());
            httpResponse.setHeaders("Content-Type","text/html");
            //根据请求计算响应
            if (httpRequest.getUrl().startsWith("/hello")){
                httpResponse.setStatus(200);
                httpResponse.setMessage("OK");
                httpResponse.writeBody("<h1>hello</h1>");
            }else {
                httpResponse.setStatus(200);
                httpResponse.setMessage("OK");
                httpResponse.writeBody("<h1>default</h1>");
            }
            //将响应写回客户端

            httpResponse.flush();
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
        HttpServerVersion2 server = new HttpServerVersion2(9091);
        server.start();
    }
}
