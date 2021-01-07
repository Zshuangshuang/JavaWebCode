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
 * Date    2021-01-07 18:59
 */
public class HttpServerV1 {
    private ServerSocket serverSocket = null;

    public HttpServerV1(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("服务器启动");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    process(clientSocket);
                }
            });
        }
    }

 public void process(Socket clientSocket){
     try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
             ){
                    //获取并解析请求
                String firstLine = bufferedReader.readLine();
                String[] firstLineTokens = firstLine.split(" ");
                String method = firstLineTokens[0];
                String url = firstLineTokens[1];
                String version = firstLineTokens[2];
                Map<String,String> headers = new HashMap<>();
                String line ="";
                while((line = bufferedReader.readLine()) != null && line.length() != 0){
                    String[] headerTokens = line.split(": ");
                    headers.put(headerTokens[0],headerTokens[1]);
                }
                System.out.printf("%s %s %s\n",method,url,version);
                for (Map.Entry<String,String> entry : headers.entrySet()){
                    System.out.println(entry.getKey()+": "+entry.getValue());
                }
                System.out.println();
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
            }

     catch (IOException e) {
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
        HttpServerV1 server = new HttpServerV1(9090);
        server.start();
    }
}
