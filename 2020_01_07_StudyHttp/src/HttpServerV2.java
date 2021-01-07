import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-07 15:24
 */
public class HttpServerV2 {
    private ServerSocket serverSocket = null;

    public HttpServerV2(int port) throws IOException {
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
                    process( clientSocket);
                }
            });
        }
    }
    public void process(Socket clientSocket){
        try {
            //建立请求并解析
            HttpRequest request = HttpRequest.build(clientSocket.getInputStream());
            System.out.println("request:"+request);
            HttpResponse response = HttpResponse.build(clientSocket.getOutputStream());
            response.setHeader("Content-Type","text/html");
            //根据响应计算请求
            if (request.getUrl().startsWith("/hello")){
                response.setStatus(200);
                response.setMessage("OK");
                response.writeBody("<h1>hello</h1>");
            }else if(request.getUrl().startsWith("/calc")){
                //根据参数形式来计算
                //1)先获取a和b的值
                String aStr = request.getParameter("a");
                String bStr = request.getParameter("b");
                int a = Integer.parseInt(aStr);
                int b = Integer.parseInt(bStr);
                int result = a+b;
                response.setStatus(200);
                response.setMessage("OK");
                response.writeBody("<h1> result = "+result+"</h1>");
            } else if(request.getUrl().startsWith("/cookieUser")){
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeader("Set-Cookie","user = ss");
                response.writeBody("<h1>cookieUser</h1>");
            } else if(request.getUrl().startsWith("/cookieTime")){
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeader("Set-Cookie","Time = "+(System.currentTimeMillis()/1000));
                response.writeBody("<h1>cookieTime</h1>");
            }else {
                response.setStatus(200);
                response.setMessage("OK");
                response.writeBody("<h1>default</h1>");
            }
            //将响应写回客户端
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
        HttpServerV2 server = new HttpServerV2(9090);
        server.start();
    }
}
