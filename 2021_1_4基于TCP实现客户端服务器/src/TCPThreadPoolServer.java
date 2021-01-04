import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 19:51
 */
public class TCPThreadPoolServer {
    private ServerSocket serverSocket = null;

    public TCPThreadPoolServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        //创建一个线程池实例
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    processConnection(clientSocket);
                }
            });
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
        TCPThreadPoolServer server = new TCPThreadPoolServer(9092);
        server.start();
    }
}
