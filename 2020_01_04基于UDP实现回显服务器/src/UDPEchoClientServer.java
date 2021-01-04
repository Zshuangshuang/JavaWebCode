import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 11:10
 */
public class UDPEchoClientServer {
    //(1)初始化socket对象
    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;

    public UDPEchoClientServer(String serverIp, int port) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = port;
        //注意：客户端初始化的时候不需要指定端口号(IP地址定位主机，端口号定位了应用程序)
        //指定了可能会催用户使用主机应用程序的时候出现一些不必要的麻烦
        socket = new DatagramSocket();
    }
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        //(2)进入主循环
        while(true){
            //a)读取用户数据
            System.out.println("输入请求:->");
            String request = scanner.nextLine();
            if (request.equals("exit")){
                System.out.println("退出");
                break;
            }
            //b)构造请求发给服务器
            //此处将request包装为packet的时候需要指定端口号和主机号
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            //c)从服务器端读取响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength()).trim();
            //d)将响应写回客户端
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UDPEchoClientServer client = new UDPEchoClientServer("127.0.0.1",9094);
        client.start();
    }
}
