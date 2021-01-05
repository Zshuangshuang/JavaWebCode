import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-05 16:13
 */
public class TCPEchoServer {
    //1)初始化ServerSocket实例
    private ServerSocket serverSocket = null;

    public TCPEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    //2)进入主循环
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            //a)获取TCP连接
            Socket clientSocket = serverSocket.accept();
            //b)处理TCP连接
            processConnection(clientSocket);
        }

    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("客户端上线~[%s:%d]",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        //获取clientSocket的流对象
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ){
            //此处实现的是长连接
            while(true){
                //①获取请求并解析
                String request = bufferedReader.readLine();
                //②计算请求
                String response = process(request);
                //③将响应写回客户端
                bufferedWriter.write(response+"\n");
                bufferedWriter.flush();
                //打印日志
                System.out.printf("[%s:%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }

        } catch (IOException e) {
            System.out.printf("客户端下线~[%s:%d]",clientSocket.getInetAddress().toString(),
                    clientSocket.getPort());
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPEchoServer server = new TCPEchoServer(9099);
        server.start();
    }
}
