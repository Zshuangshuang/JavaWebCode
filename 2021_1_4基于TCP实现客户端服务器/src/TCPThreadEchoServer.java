import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 19:30
 */
public class TCPThreadEchoServer {
    private ServerSocket serverSocket = null;

    public TCPThreadEchoServer(int port) throws IOException {
       serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            Socket clientSocket = serverSocket.accept();
            //创建一个线程负责链连接
            Thread t = new Thread(){
                @Override
                public void run() {
                    processConnection(clientSocket);
                }

            };
            t.start();
        }
    }
    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d]客户端上线~\n",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            while(true){
                //1)读取请求并建立连接
               String request =  bufferedReader.readLine();
               //2)根据请求计算响应
                String response = process(request);
                //3)将响应写回客户端
                bufferedWriter.write(response+"\n");
                bufferedWriter.flush();

                System.out.printf("[%s:%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }
        } catch (IOException e) {
            System.out.printf("[%s:%d]客户端下线~\n",clientSocket.getInetAddress().toString(),
                    clientSocket.getPort());
        }
    }
    private String  process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPThreadEchoServer server = new TCPThreadEchoServer(9091);
        server.start();
    }
}
