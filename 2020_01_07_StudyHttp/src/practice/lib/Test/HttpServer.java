package practice.lib.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-07 14:43
 */
public class HttpServer {
    private ServerSocket serverSocket = null;

    public HttpServer(int port) throws IOException {
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
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            //1)获取请求并解析
            //a)获取首行信息并解析
            //1)接收请求并解析
            String firstLine = bufferedReader.readLine();
            //a)解析首行
            String[] firstLineTokens = firstLine.split(" ");
            //①得到请求方法
            String method = firstLineTokens[0];
            //②得到url
            String url = firstLineTokens[1];
            //③得到版本号
            String version = firstLineTokens[2];
            //b)解析header:header每行都是一个键值对，用冒号+空格分割
            Map<String, String> headers = new HashMap<>();
            String line = "";
            //将headers遍历完以后，按照冒号+空格分割

                while ((line = bufferedReader.readLine()) != null && line.length() != 0) {
                    //得到每一行的键和值
                    String[] headerTokens = line.split(": ");
                    //将键和值加入头部信息
                    headers.put(headerTokens[0], headerTokens[1]);
                }
                //解析完毕，打印一个日志观察解析是否正确
                //打印首行
                System.out.printf("%s %s %s\n", method, url, version);
                //打印header信息
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                //c)加上空行，表示header的结束
                bufferedWriter.write("\n");
                //2)根据请求计算响应
                String response = "";
                //根据url的域名来计算响应
                if (url.equals("/ok")) {
                    //a)将响应的首行写回:版本号+空格+状态码+空格+状态码描述
                    bufferedWriter.write(version + " 200 OK\n");
                    response = "<h1>hello</h1>";
                } else if (url.equals("/notfound")) {
                    bufferedWriter.write(version + " 404 Not Found\n");
                    response = "<h1>Not Found</h1>";
                } else if (url.equals("/seeother")) {
                    bufferedWriter.write(version + " 303 See Other\n");
                    bufferedWriter.write("Location: http://www.sogou.com\n");
                    response = "";
                } else {
                    bufferedWriter.write(version + "200 OK\n");
                    response = "<h1>default</h1>";
                }
                //将响应写回客户端
                bufferedWriter.write("Content-Type: text/html");
                bufferedWriter.write("Content-Length: " + response.getBytes().length + "\n");
                //2)header写回完毕之后，需要写回空行
                bufferedWriter.write("\n");
                //3)将body写回
                bufferedWriter.write(response);
                //刷新缓冲区
                bufferedWriter.flush();
            }
        
         catch(IOException e){
                e.printStackTrace();
            }finally{
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(9090);
        server.start();
    }
}
