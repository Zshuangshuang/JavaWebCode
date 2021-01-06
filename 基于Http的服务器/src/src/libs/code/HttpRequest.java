package src.libs.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:表示一个Http请求并解析
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 20:29
 */
public class HttpRequest {
    private String method;
    private String url;
    private String version;
    private Map<String, String> headers = new HashMap<>();
    //parameters表示URL中的参数，类型也是键值对，所以用map表示
    private Map<String, String> parameters = new HashMap<>();

    //请求的构造，用工厂模式完成
    //此处的inputStream就是从socket中获取的参数
    //此处的逻辑中，不能把bufferedReader写入try()中
    //一旦写入try()中就意味着会被关闭，会影响clientSocket的状态，要等到请求处理完毕再关闭
    public static HttpRequest build(InputStream inputStream) throws IOException {
        HttpRequest httpRequest = new HttpRequest();
        //build实质就是解析请求过程
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //1.解析首行
        String firstLine = bufferedReader.readLine();
        String[] firstLineTokens = firstLine.split(" ");
        httpRequest.method = firstLineTokens[0];
        httpRequest.url = firstLineTokens[1];
        httpRequest.version = firstLineTokens[2];

        //2.解析url中的参数
        int pos = httpRequest.url.indexOf("?");
        //判断url中是否有?，如果没有问号就不用找了
        if (pos != -1){
            //此处的parameters是希望可以找到url中所有的参数
            //pos位置是?的位置
            //url形如：index.html?admin=a&password=123
            //parameters就相当于admin=a&password=123
            String parameters = httpRequest.url.substring(pos+1);
            //解析键值对的方法
            parseKV(parameters,httpRequest.parameters);
        }
        //3.解析请求头
        String line = "";
        while((line = bufferedReader.readLine()) != null && line.length() != 0){
            String[] headerToken = line.split(": ");
            httpRequest.headers.put(headerToken[0],headerToken[1]);
        }
        //4.解析body(暂不考虑)
        return httpRequest;

    }

    private static void parseKV(String input, Map<String, String> output) {
        //1、按照输入的键值对，按照&分开
        String[] kvTokens = input.split("&");
        //2、将上面的结果用=分开,得到键和值
        for (String kv: kvTokens){
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

    public String  getHeaders(String key) {
        return headers.get(key);
    }

    public String  getParameters(String key) {
        return parameters.get(key);
    }
}
