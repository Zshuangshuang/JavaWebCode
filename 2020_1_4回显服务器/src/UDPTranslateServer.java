import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 10:26
 */
public class UDPTranslateServer extends UDPEchoServer {
    Map<String,String> translate = new HashMap<>();
    public UDPTranslateServer(int port) throws SocketException {
        super(port);
        translate.put("cat","小猫咪");
        translate.put("dog","小狗");
        translate.put("lucky","幸运");
    }

    @Override
    public String process(String request) {
        return translate.getOrDefault(request,"超出我的能力");
    }

    public static void main(String[] args) throws IOException {
        UDPTranslateServer server = new UDPTranslateServer(9294);
        server.start();
    }
}
