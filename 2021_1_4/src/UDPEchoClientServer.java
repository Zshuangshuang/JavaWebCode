import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 15:33
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
            //a)读取用户请求
            System.out.println("请输入请求：->");
            String request = scanner.nextLine();
            if (request.equals("exit")){
                System.out.println("退出");
                break;
            }
            //b)构造请求,也就是将String类型的request包装为Packet数据包发送给服务器
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            //c)从服务器读取请求
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(requestPacket.getData(),0,responsePacket.getLength()).trim() ;
            //d)将响应写回客户端
            System.out.println(response);
        }

    }

    public static void main(String[] args) throws IOException {
        UDPEchoClientServer client = new UDPEchoClientServer("127.0.0.1",9096);
        client.start();
    }
}
