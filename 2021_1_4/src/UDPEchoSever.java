import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Author:ZouDouble
 * Description:基于UDP实现回显服务器
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 15:24
 */
public class UDPEchoSever {
    private DatagramSocket socket = null;
    //(1)初始化socket对象
    public UDPEchoSever(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }
    //(2)进入主循环
    public void start() throws IOException {
        System.out.println("服务器开始启动");
        while(true){
            // a)处理解析请求
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            String request = new String(requestPacket.getData(),0,requestPacket.getLength()).trim();
            // b)根据请求计算响应
            String response = process(request);
            //c)将响应写回客户端
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            socket.send(responsePacket);
            //打印服务器日志
            System.out.printf("[%s:%d] req:%s resp:%s\n",requestPacket.getAddress(),requestPacket.getPort(),
                    request,response);
        }
    }

   public String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoSever sever = new UDPEchoSever(9096);
        sever.start();
    }
}
