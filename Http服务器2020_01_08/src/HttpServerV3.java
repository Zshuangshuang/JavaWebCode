import com.sun.xml.internal.bind.v2.TODO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * (1)支持返回一个完整的静态页面
 * (2)解析处理cookie(将cookie解析为键值对)
 * (3)解析处理body(将body中的数据解析为键值对)
 * (4)实现简单的session功能->实现简单的登录功能
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-08 20:55
 */
public class HttpServerV3 {
private ServerSocket serverSocket = null;

    public HttpServerV3(int port) throws IOException {
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
    public void process(Socket clientSocket){
        try{
            //1)读取请求并解析
        HttpRequest request = HttpRequest.build(clientSocket.getInputStream());
        HttpResponse response = HttpResponse.build(clientSocket.getOutputStream());
            //2)根据请求计算响应
            //根据不同Http方法拆分成对应逻辑
            if ("GET".equalsIgnoreCase(request.getMethod())){
                doGet(request,response);
            }else if("POST".equalsIgnoreCase(request.getMethod())){
                dpPost(request,response);
            }else {
                //返回405这样的状态码
                response.setStatus(405);
                response.setMessage("Method Not Allowed");
            }
            //3)将响应写回客户端
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

    private void dpPost(HttpRequest request, HttpResponse response) {
    }

    private void doGet(HttpRequest request, HttpResponse response) throws IOException {
        //1)能够支持返回一个html文件
        if (request.getUrl().startsWith("/index.html")){
            //获取一个类的类对象,获取当前类的类加载器
            InputStream inputStream = HttpServerV3.class.getClassLoader().getResourceAsStream("index.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                response.writeBody(line+"\n");
            }
            bufferedReader.close();
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServerV3 serverV3 = new HttpServerV3(9090);
        serverV3.start();
    }
}
