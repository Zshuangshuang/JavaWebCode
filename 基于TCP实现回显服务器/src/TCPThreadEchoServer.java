import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-05 16:32
 */
public class TCPThreadEchoServer {
    private ServerSocket serverSocket = null;

    public TCPThreadEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        //和TCP建立连接
        while(true){
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(){
                @Override
                public void run(){
                    processConnection(clientSocket);
                }
            };
            thread.start();
        }

    }
    private void processConnection(Socket clientSocket){
        System.out.printf("客户端上线~[%s:%d]",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ){
            while(true){
                //①获取请求并解析
                String request = bufferedReader.readLine();
                //②根据请求计算响应
                String response = process(request);
                //③将响应写回客户端
                bufferedWriter.write(request+"\n");
                bufferedWriter.flush();
                //打印日志
                System.out.printf("[%s:%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }

        } catch (IOException e) {
            System.out.printf("客户端上线~[%s:%d]",clientSocket.getInetAddress().toString(),
                    clientSocket.getPort());
        }
    }
    private String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPThreadEchoServer server = new TCPThreadEchoServer(9092);
        server.start();
    }
}
