import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:基于UDP实现回显服务器的客户端
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 10:12
 */
public class UDPEchoClientServer {
    //(1)初始化socket对象
    DatagramSocket socket = null;
    private String serverIP;
    private int serverPort;
//客户端在构造的时候不需要绑定端口号,端口号对应的是主机的应用程序
    public UDPEchoClientServer( String serverIP, int serverPort) throws SocketException {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        socket = new DatagramSocket();
    }
    //(2)进入主循环
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("->输入请求");
            // a)读取用户输入的数据
            String request = scanner.nextLine();
            if (request.equals("exit")){
                System.out.println("退出~");
                break;
            }
            // b)构造请求发送给服务器
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIP),serverPort);
            socket.send(requestPacket);
            // c)从服务器读取响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength()).trim();
            // d)将响应写回客户端
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UDPEchoClientServer server = new UDPEchoClientServer("127.0.0.1",9294);
        server.start();
    }


}
