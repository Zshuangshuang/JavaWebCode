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
 * Date    2021-01-06 14:18
 */
public class HttpServerVersion1 {
    //Http底层要基于TCP
    private ServerSocket serverSocket = null;

    public HttpServerVersion1(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器开始启动");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = serverSocket.accept();
            //此处使用短连接
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                        process(clientSocket);
                    }
            });

        }
    }

    private void process(Socket clientSocket)  {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            //以下部分都要基于Http协议来完成
            //(1)获取请求并解析
            // a)解析首行
            String firstLine = bufferedReader.readLine();
            String[] firstLineTokens = firstLine.split(" ");
            String method = firstLineTokens[0];
            String url = firstLineTokens[1];
            String version = firstLineTokens[2];
            // b)解析header:按行读取，根据冒号+空格分割键值对
            Map<String,String> headers = new HashMap<>();
            String line = "";
            while((line = bufferedReader.readLine()) != null && line.length() != 0){
                //不能直接按照冒号分割，因为refer重定向地址中可能会有冒号或者空格，需要冒号+空格来分割
                String[] headersToken = line.split(": ");
                headers.put(headersToken[0],headersToken[1]);
            }
            // c)解析body：暂时不考虑
            //打印日志
            System.out.printf("%s %s %s\n",method,url,version);
            for (Map.Entry<String,String> entry :headers.entrySet()){
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
            System.out.println();
            //(2)根据请求计算响应
            String response = "";
            if (url.equals("/ok")){
                bufferedWriter.write(version+" 200 OK\n");
                response = "<h1>hello</h1>";
            }else if (url.equals("/notfound")){
                bufferedWriter.write(version+" 404 Not Found\n");
                response = "<h1>Not Found</h1>";
            }else if(url.equals("/seeother")){
                bufferedWriter.write(version+" 303 See Other\n");
                bufferedWriter.write("Location: http://www.sogou.com\n");
                response = "";
            }
            else {
                bufferedWriter.write(version+" 200 OK\n");
                response = "<h1>default</h1>";
            }
            //(3)将响应写回客户端
            bufferedWriter.write(version+"200 OK\n");
            bufferedWriter.write("Content-Type:"+"txt.html\n");
            bufferedWriter.write("Content-Length:"+response.getBytes().length+"\n");
            bufferedWriter.write("\n");
            bufferedWriter.write(response);
            bufferedWriter.flush();

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
        HttpServerVersion1 server = new HttpServerVersion1(9090);
        server.start();
    }
}
