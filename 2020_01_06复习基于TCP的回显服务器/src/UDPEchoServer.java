import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 9:18
 */
public class UDPEchoServer {
    private DatagramSocket socket = null;

    public UDPEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
          DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
          socket.receive(requestPacket);
          String request = new String(requestPacket.getData(),0,requestPacket.getLength());
          String response = process(request);
          DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                  requestPacket.getSocketAddress());
          socket.send(responsePacket);
            System.out.printf("[%s:%d] req:%s resp:%s\n",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoServer server = new UDPEchoServer(9090);
        server.start();
    }
}
