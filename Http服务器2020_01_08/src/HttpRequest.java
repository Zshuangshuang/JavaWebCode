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
 * Date    2021-01-08 20:57
 */
public class HttpRequest {
    private String method;
    private String url;
    private String version;
    private Map<String,String> headers = new HashMap<>();//解析headers数据
    private Map<String,String> parameters = new HashMap<>();//解析url和body数据
    private Map<String,String> cookies = new HashMap<>();//解析cookie数据
    private String body;
    public static HttpRequest build(InputStream inputStream) throws IOException {
        HttpRequest request = new HttpRequest();
        //1)处理首行信息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String firstLine = bufferedReader.readLine();
        String[] firstLineTokens = firstLine.split(" ");
        request.method = firstLineTokens[0];
        request.url = firstLineTokens[1];
        request.version = firstLineTokens[2];
        //2)解析url
        int pos = request.url.indexOf("?");
        if (pos != -1){
            String queryString = request.url.substring(pos+1);
            parseKv(queryString,request.parameters);
        }
        //3)解析header数据
        String line = "";
        while((line = bufferedReader.readLine()) != null && line.length() != 0){
            String[] headersTokens = line.split(": ");
            request.headers.put(headersTokens[0],headersTokens[1]);
        }
        //4)解析cookie
       String cookie = request.headers.get("Cookie");
        if (cookie != null){
            //4)把cookie进行解析
            parseCookie(cookie,request.cookies);
        }
        //5)解析body
        if ("POST".equalsIgnoreCase(request.method)
        ||"PUT".equalsIgnoreCase(request.method)){
            //读取body
            //①先根据contentLength计算body的长度
            //contentLength单位是字节
            int contentLength = Integer.parseInt(request.headers.get("Content-Length"));
            //由于bufferedReader的单位是字符，因此需要创建一个字符数组
            //假如contentLength有100个字节，那么创建的char数组就会有100个字符，也就是200个字节
            char[] buffer = new char[contentLength];
            //len表示真实读到的长度
            int len = bufferedReader.read(buffer);
            request.body = new String(buffer,0,len);
           parseKv(request.body,request.parameters);
        }
        return request;
    }

    private static void parseCookie(String cookie, Map<String, String> cookies) {
        String[] kvTokens = cookie.split("; ");
        for (String kv : kvTokens){
            String[] result = kv.split("=");
            cookies.put(result[0],result[1]);
        }
    }

    private static void parseKv(String input, Map<String, String> output) {
        String[] kvTokens = input.split("&");
        for (String kv :kvTokens){
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

    public String getBody() {
        return body;
    }
    public String getParameters(String key){
        return parameters.get(key);
    }
    public String getHeader(String key){
        return headers.get(key);
    }
    public String getCookie(String key){
        return cookies.get(key);
    }
}
