import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:这样的服务器有缺陷，不允许多用户访问，由于串行执行
 * 解决这个问题需要用到多线程，让accept和 建立连接并发执行
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 20:27
 */
public class TCPEchoServer {
    //(1)初始化socket对象
    //(2)进入主循环
    //a)和内核中获取TCP连接
    //b)处理TCP连接
    //①读取并解析请求
    //②根据请求计算响应
    //③将响应写回客户端
    //(1)初始化socket对象
    //此处的serverSocket负责和客户端建立连接
    ServerSocket serverSocket = null;
    public TCPEchoServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    //进入主循环
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            //a)从内核中获取TCP连接
            //此处的clientSocket负责和客户端进行交互
            Socket clientSocket = serverSocket.accept();
            //b)处理TCP连接
            processConnection(clientSocket);
        }
    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("客户端上线[%s:%d]",clientSocket.getInetAddress().toString(),clientSocket.getPort());
        //clientSocket负责和客户端进行交互
        //①先获取clientSocket的流对象，将字节流转换为字符流，因为回显服务器只要使用的是文本文档
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
                ){
            //这里采用长连接的方式，避免多次建立连接和断开连接过程中造成资源浪费
            //这里当且仅当客户端断开连接的时候就结束了
            while(true){
                //①接收和解析请求
                //此处规定了用户端发送的请求和服务器端接收请求的格式都是以行为单位的，相当于自定义协议
                String request = bufferedReader.readLine();
                //②根据请求计算响应
                String response = process(request);
                //③将响应写回客户端
                bufferedWriter.write(request+"\n");
                //此处由于 bufferedWriter是将内容写到了缓冲区中，还没有写入socket文件中，需要刷新缓冲区
                bufferedWriter.flush();
                //打印日志
                System.out.printf("[%s:%d] req:%s resp:%s\n",clientSocket.getInetAddress().toString(),
                        clientSocket.getPort(),request,response);
            }

        } catch (IOException e) {
            System.out.printf("客户端下线[%s:%d]",clientSocket.getInetAddress().toString(),clientSocket.getPort());
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        TCPEchoServer server = new TCPEchoServer(9092);
        server.start();
    }
}
