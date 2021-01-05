import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Author:ZouDouble
 * Description:基于UDP实现回显服务器
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-05 9:49
 */
public class UDPEchoServer {
    private DatagramSocket socket = null;

    public UDPEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        while(true){
            //a)接收并解析请求数据
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0,requestPacket.getLength()).trim();
            //b)根据请求计算响应
            String response = process(request);
            //c)将响应写回客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);
            //打印日志
            System.out.printf("[%s:%d] req:%s resp:%s\n",requestPacket.getAddress(),requestPacket.getPort(),
                    request,response);
        }
    }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoServer server = new UDPEchoServer(9097);
        server.start();
    }
}
