import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-05 17:20
 */
public class TCPEchoServer {
    private ServerSocket serverSocket = null;

    //初始化serverSocket时需要绑定端口号
    //serverSocket负责与客户端进行连接
    public TCPEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            //负责与客户端交互
            //与TCP建立连接
            Socket clientSocket = serverSocket.accept();
            //处理连接
            processConnection(clientSocket);
        }


    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("客户端上线 [%s:%d]",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        //TCP是面向字节流的，由于是回显服务器主要针对字符流文件，因此这里需要将字节流文件字符流化
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ){
            while(true){
                //获取请求并解析
                //注意此处readLine()规定了读取是按照行读取的，同样写数据必须按行写，相当于自定义协议
                String request = bufferedReader.readLine();
                //根据请求计算响应
                String response = process(request);
                //将响应写回客户端
                //这里之所以加上换行符的原因是因为要按行写数据，需要遵循自定义协议
                bufferedWriter.write(response+"\n");
                //由于 bufferedWriter是带有缓冲区的，没有刷新的话数据就在缓冲区中，没有真正写到socket中
                //需要手动刷新
                bufferedWriter.flush();
                //打印日志
                System.out.printf("[%s:%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPEchoServer server = new TCPEchoServer(9090);
        server.start();
    }
}
