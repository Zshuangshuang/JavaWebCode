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
 * Date    2021-01-07 8:55
 */
public class HttpServerV1 {
    //Http底层是基于TCP协议完成的，所以开发的时候需要按照TCP协议来实现
    private ServerSocket serverSocket = null;

    public HttpServerV1(int port) throws IOException {
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
    private void process(Socket clientSocket){
        //由于Http协议是基于文本实现的，所以仍然需要将字节流转化为字符流
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))

        ) {
            while(true){
                //1)获取并解析请求
                //a)解析首行
                String firstLine = bufferedReader.readLine();
                //首行内容分别是请求方法，url以及版本号，用空格分开
                String[] firstLineTokens = firstLine.split(" ");
                String method = firstLineTokens[0];
                String url = firstLineTokens[1];
                String version = firstLineTokens[2];
                //b)解析header:按行读取，然后用冒号+空格分割
                Map<String,String> headers = new HashMap<>();
                String line = "";
                //当出现空行或者读到的内容为空，证明头部为空
                while((line = bufferedReader.readLine()) != null && line.length() != 0){
                    //此处开始用冒号+空格分割，不能直接用冒号分割，因为在refer中的url也有冒号
                    String[] headersTokens = line.split(": ");
                    headers.put(headersTokens[0],headersTokens[1]);
                }
                //解析完毕，写一个打印日志观察请求是否正确
                //打印首行信息
                System.out.printf("%s %s %s\n",method,url,version);
                //打印header信息
                for (Map.Entry<String,String> entry:headers.entrySet()){
                    System.out.println(entry.getKey()+": "+entry.getValue());
                }
                //请求头完毕，打印空行
                System.out.println("\n");
                //2)根据请求计算响应
                String response = "";
                if (url.equals("/ok")){
                    //a)将响应的首行写回:版本号+空格+状态码+空格+状态码描述
                    bufferedWriter.write(version+" 200 Ok\n");
                    response = "<h1>hello</h1>";
                }else if (url.equals("/notfound")){
                    //a)将响应的首行写回:版本号+空格+状态码+空格+状态码描述
                    bufferedWriter.write(version+" 404 Not Found\n");
                    response = "<h1>Not Found</h1>";
                }else if (url.equals("/seeother")){
                    //a)将响应的首行写回:版本号+空格+状态码+空格+状态码描述
                    bufferedWriter.write(version+" 303 See Other\n");
                    bufferedWriter.write("Location: http://www.sogou.com\n");
                    response = "";
                }else {
                    //a)将响应的首行写回:版本号+空格+状态码+空格+状态码描述
                    bufferedWriter.write(version+" 200 Ok\n");
                    response = "<h1>default</h1>";
                }


                //b)将header写回
                bufferedWriter.write("Content-Type: text/html\n");
                //此处的Content-Length不能写成response.length，原因是这里的Content-Length表示的是字节的长度
                bufferedWriter.write("Content-Length: "+response.getBytes().length+"\n");
                //写空行
                bufferedWriter.write("\n");
                //写body
                bufferedWriter.write(response);
                bufferedWriter.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            {
                //此处实现的是短连接，一次交互完毕自动关闭连接
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        HttpServerV1 server = new HttpServerV1(9090);
        server.start();
    }
}
