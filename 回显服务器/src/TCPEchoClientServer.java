import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 21:42
 */
public class TCPEchoClientServer {
    //(1)启动客户端和服务端建立连接
    //(2)进入主循环
    //a)读取用户输入的数据
    //b)构造请求
    //c)读取服务器请求
    //d)将请求写回服务器
private Socket socket = null;

    //初始化socket的时候，就已经和服务端建立了连接
    public TCPEchoClientServer(String serverIp,int serverPort) throws IOException {
        socket = new Socket(serverIp,serverPort);
    }
    public void start(){
        System.out.println("客户端启动");
        Scanner scanner = new Scanner(System.in);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ){
            while(true){
                //a)读取用户输入
                String request = scanner.nextLine();
                if ("exit".equals(request)){
                    System.out.println("退出");
                    break;
                }
                //b)构造请求，由于服务器中已经规定读取的数据是按行读取，因此客户端在发送请求的时候也应该遵循
                bufferedWriter.write(request+"\n");
                bufferedWriter.flush();
                //c)读取服务器的响应
                String response = bufferedReader.readLine();
                //d)将响应写回客户端
                System.out.println(response);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        TCPEchoClientServer client = new TCPEchoClientServer("127.0.0.1",9092);
        client.start();
    }
}
