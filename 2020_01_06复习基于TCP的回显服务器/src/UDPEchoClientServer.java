import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 9:24
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
            System.out.println("输入请求->");
            String request = scanner.nextLine();
            if ("exit".equals(request)){
                System.out.println("退出");
                break;
            }
            DatagramPacket requestPacket = new DatagramPacket(request.getBytes(),request.getBytes().length,
                    InetAddress.getByName(serverIp),serverPort);
            socket.send(requestPacket);
            DatagramPacket responsePacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(responsePacket);
            String response = new String(responsePacket.getData(),0,responsePacket.getLength()).trim();
            System.out.println(response);
        }
    }

    public static void main(String[] args) throws IOException {
        UDPEchoClientServer client = new UDPEchoClientServer("127.0.0.1",9090);
        client.start();
    }
}
