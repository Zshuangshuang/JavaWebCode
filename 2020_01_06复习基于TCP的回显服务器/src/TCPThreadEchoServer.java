import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 9:06
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
            Thread thread = new Thread(){
                @Override
                public void run() {
                    processConnection(clientSocket);
                }
            };
            thread.start();
        }
    }
    private void processConnection(Socket clientSocket){
        System.out.printf("客户端上线[%s:%d]",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ){
            while(true){
                String request = bufferedReader.readLine();
                String response = process(request);
                bufferedWriter.write(response+"\n");
                bufferedWriter.flush();
                System.out.printf("[%s:%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }

        } catch (IOException e) {
            System.out.printf("客户端下线[%s:%d]",clientSocket.getInetAddress().toString(),
                    clientSocket.getPort());
        }

    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPThreadEchoServer server = new TCPThreadEchoServer(9090);
        server.start();
    }
}
