import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:绑定端口是ServerSocket类完成的，socket类并没有绑定端口
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-05 10:21
 */
public class TCPEchoClientServer {
    //1)初始化socket对象
    //2)进入主循环
    //a)读取用户请求并解析
    //b)将请求发送给服务器
    //c)读取服务器响应
    //d)将响应写回客户端
    private Socket socket = null;

    public TCPEchoClientServer(String serverIp,int serverPort) throws IOException {
        //此处实例化结是与服务器端进行连接
        socket = new Socket(serverIp,serverPort);
    }
    public void start(){
        System.out.println("客户端启动");
        Scanner scanner = new Scanner(System.in);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ){
            while(true){
                //a)读取用户请求并解析
                System.out.println("输入请求->:");
                String request = scanner.nextLine();
                if ("exit".equals(request)){
                    System.out.println("退出");
                    break;
                }
                //b)构造请求并发送(此处加上\n是为了和服务端对应的readLine相对应,满足自定义协议)将文件写到了缓冲区
                // 文件没有真正写到socket中，需要手动刷新
                bufferedWriter.write(request+"\n");
                bufferedWriter.flush();
                //c)读取响应数据
                String response = bufferedReader.readLine();
                //d)将响应写回客户端
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPEchoClientServer client = new TCPEchoClientServer("127.0.0.1",9098);
        client.start();
    }
}
