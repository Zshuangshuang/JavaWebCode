import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-07 15:24
 */
public class HttpRequest {
    private String method;//请求方法
    private String url;//url
    private String version;//版本号
    private Map<String,String> headers = new HashMap<>();//headers部分
    private Map<String,String> parameters = new HashMap<>();//解析url中的参数部分
    //使用工厂模式进行构造
    public static HttpRequest build(InputStream inputStream) throws IOException {
        //这里不用try()包裹的原因是：一旦包裹，build方法一结束
        // inputStream就关闭了，会影响socket对象的状态，因为inputStream就是传输过来的socket对象
        //等到请求解析完毕了在同一关闭
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        HttpRequest request = new HttpRequest();
        //1)解析首行
        String firstLine = bufferedReader.readLine();
        String[] firstLineTokens = firstLine.split(" ");
        //①得到请求方法
        request.method = firstLineTokens[0];
        //②得到url
        request.url = firstLineTokens[1];
        //③得到版本号
        request.version = firstLineTokens[2];
        //2)解析url中的参数
        //url形如 index.html?name=a&password=123
        int pos = request.url.indexOf("?");
        if (pos != -1){
            //得到name=a&password=123
            String parameters = request.url.substring(pos+1);
            //解析参数部分:
            parseKv(parameters,request.parameters);
        }
        //3)解析header部分
        String line = "";
        while((line = bufferedReader.readLine()) != null && line.length() != 0){
            String[] headerTokens = line.split(": ");
            request.headers.put(headerTokens[0],headerTokens[1]);
        }
        //解析body(暂不考虑)
        return request;

    }
    /**
     * 解析url中的键值对的方法
     * */
    private static void parseKv(String input, Map<String, String> output) {
        String[] kvTokens = input.split("&");
        for (String kv:kvTokens){
            String[] result = kv.split("=");
            output.put(result[0],result[1]);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
    public String getHeaders(String key){
        return headers.get(key);
    }
    public String getParameter(String key){
        return parameters.get(key);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", headers=" + headers +
                ", parameters=" + parameters +
                '}';
    }
}
