import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 16:04
 */
public class TCPEchoServer {
    //(1)初始化服务器
    //(2)进入主循环
    // a)从内核中获取TCP连接
    // b) 处理TCP连接
    //①读取请求并解析
    //②根据请求计算响应
    //③将响应写回客户端
    //serverSocket负责和客户端建立连接
    private ServerSocket serverSocket = null;

    public TCPEchoServer(int port) throws IOException {
       serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            //Socket负责和客户端交互
            //a)从内核中获取TCP连接:由操作系统内核管理
          Socket clientSocket = serverSocket.accept();
            //b)处理连接
            processConnection(clientSocket);
        }
    }

    private void processConnection(Socket clientSocket) {
        System.out.printf("[%s:%d]客户端上线",clientSocket.getInetAddress().toString(),
                clientSocket.getPort());
        //通过clientSocket来和客户端交互
        //1)先获取到clientSocket中的流对象
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ){
            //实现长连接版本的服务器，避免多次建立连接消耗资源
            //一次连接过程中需要处理多次请求和响应
            //这个循环当客户端断开连接的时候就结束了
            //当客户端断开连接的时候调用readLine方法或者write方法就会触发IOException异常而终止
            while(true){
                //①读取请求并解析:此处隐含客户端发送的数据是按行发送(每条数据发一行,相当于应用层的自定义协议)
                //此处的readLine代表着对应客户端的发送格式：必须是按行发送
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

            System.out.printf("[%s:%d]客户端下线",clientSocket.getInetAddress().toString(),
                    clientSocket.getPort());
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
