import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Author:ZouDouble
 * Description:基于UDP实现最简单的回显服务器
 * 回显服务器：客户端发送给服务器什么，服务器就原封不动的返回给客户端并显示
 * UDP是不可靠的面向无连接的协议
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 9:33
 */
public class UDPEchoServer {
    //(1)初始化socket对象，在服务器端需要首先将服务器和对应的端口号绑定，目的是使客户端能够找到对应端口号的服务器建立连接
    DatagramSocket socket = null;

    public UDPEchoServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    //(2)接受请求,进入主循环
    public void start() throws IOException {
        System.out.println("服务器开始启动");
        while(true){
            // a)读取数据并解析
            // DatagramPacket是UDP协议中收发数据的基本单位
            DatagramPacket requestPacket = new DatagramPacket(new byte[4096],4096);
            //此处由于服务器不知道客户端什么时候发送请求数据，因此在不停等待，当服务器启动时会调用这里的receive方法
            // 可能会触发异常，所以需要处理异常
            socket.receive(requestPacket);
            //由于此处的请求是一个字节数组，不太方便处理，因此将它转化为字符类型
            String request = new String(requestPacket.getData(),0,requestPacket.getLength()).trim();
            // b)根据请求计算响应
            String response = process(request);
            // c)将响应写回客户端
            //由于response是一个String 类型，这里要把它封装为packet类型的对象，然后需要指定将这个包发给谁
            DatagramPacket responsePacket = new DatagramPacket(response.getBytes(),response.getBytes().length,requestPacket.getSocketAddress());
            socket.send(requestPacket);
            //打印请求日志
            System.out.printf("[%s : %d] req:%s  resp:%s\n",requestPacket.getAddress().toString(),
                    requestPacket.getPort(),request,response);
        }
    }

    public String process(String request){
        return request;
    }

    public static void main(String[] args) throws IOException {
        UDPEchoServer server = new UDPEchoServer(9294);
        server.start();
    }
}
