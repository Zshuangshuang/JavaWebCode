import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-04 11:40
 */
public class TranslateServer  extends UDPEchoServer{
    Map<String,String > dictionary = new HashMap<>();
    public TranslateServer(int port) throws SocketException {
        super(port);
        dictionary.put("happy","开心");
        dictionary.put("lucky","幸运");
        dictionary.put("nice","美好");
    }

    @Override
    public String process(String request) {
        return dictionary.getOrDefault(request,"超出我的词库啦，我会继续努力学习的~");
    }

    public static void main(String[] args) throws IOException {
        TranslateServer translate = new TranslateServer(9094);
        translate.start();
    }
}
