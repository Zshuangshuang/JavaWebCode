import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:负责获取并解析http请求
 是一个反序列化的操作
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-11 9:05
 */
public class HttpRequest {
    private String method;//http首行请求方法
    private String url;//http首行网址
    private String version;//http请求报文首行版本号
    //headers用来储存http请求中的请求头信息
    private Map<String,String> headers = new HashMap<>();
    //parameters用来储存http请求中解析的url和body中的参数
    private Map<String,String> parameters = new HashMap<>();
    //cookies用来储存解析cookie中的信息
    private Map<String,String> cookies = new HashMap<>();
    //body用来表示http报文中的body信息(只有post/put方法中才有body)
    private String body;
    //用工厂方法来构建HttpRequest
    public static HttpRequest build(InputStream inputStream) throws IOException {
        HttpRequest request = new HttpRequest();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //解析首行
        String firstLine = bufferedReader.readLine();
        String[] firstLineTokens = firstLine.split(" ");
        request.method = firstLineTokens[0];
        request.url = firstLineTokens[1];
        request.version = firstLineTokens[2];
        //解析url
        int pos = request.url.indexOf("?");
        if (pos != -1){
            String queryString = request.url.substring(pos+1);
            parseKv(queryString,request.parameters);
        }
        //循环处理header
        String line = "";
        while((line = bufferedReader.readLine()) != null && line.length() != 0){
            String[] headerTokens = line.split(": ");
            request.headers.put(headerTokens[0],headerTokens[1]);
        }
        //解析cookie
        String cookie = request.headers.get("Cookie");
        if (cookie != null){
            parseCookies(cookie,request.cookies);
        }
        //解析body
        if ("POST".equalsIgnoreCase(request.method)
                || "PUT".equalsIgnoreCase(request.method)){
            //先将Body读取出来
            int contentLength =Integer.parseInt(request.headers.get("Content-Length")) ;
            char[] buffer = new char[contentLength];
            int len =  bufferedReader.read(buffer);
            request.body = new String(buffer,0,len);
            parseKv(request.body,request.parameters);
        }
        return request;
    }
    private static void parseCookies(String cookie, Map<String, String> cookies) {
        String[] kvTokens = cookie.split("; ");
        for (String kv : kvTokens){
            String[] result = kv.split("=");
            cookies.put(result[0],result[1]);
        }
    }

    private static void parseKv(String queryString, Map<String, String> parameters) {
        //①现将读取到的queryString按照&分割，得到形如 name=a; password=123;
        String[] kvTokens = queryString.split("&");
        //②江都区到的queryString继续按照=分割,得到url参数中的键和值将他们放入hash表中
        for (String kv : kvTokens){
            String[] result = kv.split("=");
            parameters.put(result[0],result[1]);
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
    public String getHeader(String key){
        return headers.get(key);
    }
    public String getCookie(String key){
        return cookies.get(key);
    }
    public String getParameter(String key){
        return parameters.get(key);
    }
}
