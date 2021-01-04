
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 10:53
 */
public class UDPEchoServer {
    //(1)初始化socket对象
    //在初始化的过程中要绑定端口号，使得主机可以找到对应的应用程序
    private DatagramSocket socket = null;
    public UDPEchoServer(int port) throws SocketException {
       socket = new DatagramSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器开始启动");
        //(2)进入主循环
        //主循环是一个死循环，因为服务器端也不知道客户端会什么时候发送请求，所以要随时准备
        while(true){
           // a)解析请求数据
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            socket.receive(requestPacket);
            //由于得到的请求是一个字节数组,处理不方便,此处就把它转换为String类型
            String request = new String(requestPacket.getData(),0,requestPacket.getLength());
            // b)根据请求计算响应
            String response = process(request);
            //注意：将response包装诶Packet的时候要指定它将数据包发给谁
            // requestPacket.getSocketAddress()就指定了数据包将要发送的目的IP和端口号
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,
                    requestPacket.getSocketAddress());
            //c)将响应写回客户端
            socket.send(responsePacket);
            //打印响应请求日志
            System.out.printf("[%s:%d] req:%s resp:%s\n",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
        }

    }
    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoServer server = new UDPEchoServer(9094);
        server.start();
    }
}
