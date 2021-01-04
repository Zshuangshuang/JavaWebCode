import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 20:08
 */
public class UDPEchoClientServer {
    private DatagramSocket socket = null;
    private String serverIp;
    private int serverPort;

    public UDPEchoClientServer(String serverIp, int serverPort) throws SocketException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        socket = new DatagramSocket();
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            //1)读取用户数据
            System.out.println("->");
            String request = scanner.nextLine();
            if ("exit".equals(request)){
                System.out.println("退出");
                break;
            }
           //2)构造请求
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            //3)接收服务器端的响应
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength()).trim();
            //4)将响应写回客户端
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UDPEchoClientServer client = new UDPEchoClientServer("127.0.0.1",9294);
        client.start();
    }
}
