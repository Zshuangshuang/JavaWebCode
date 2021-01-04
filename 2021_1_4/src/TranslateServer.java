import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 15:44
 */
public class TranslateServer  extends UDPEchoSever{
    Map<String,String > dic = new HashMap<>();
    public TranslateServer(int port) throws SocketException {
        super(port);
    }

    @Override
    public String process(String request) {
        return dic.getOrDefault(request,"超出我的能力范围~");
    }
}
