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
 * Date    2021-01-09 14:41
 */
public class HttpRequest {
    private String method;
    private String url;
    private String version;
    private Map<String,String> headers = new HashMap<>();
    //parameters中存放解析body和url的参数
    private Map<String,String> parameters = new HashMap<>();
    private Map<String,String> cookies = new HashMap<>();
    private String body;
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
            parseCookie(cookie,request.cookies);
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

    private static void parseCookie(String cookie, Map<String, String> cookies) {
        String[] kvTokens = cookie.split("; ");
        for (String kv : kvTokens){
            String[] result = kv.split("=");
            cookies.put(result[0],result[1]);
        }
    }

    private static void parseKv(String queryString, Map<String, String> parameters) {
        String[] kvTokens = queryString.split("&");
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
    public String getHeader(String key){
        return headers.get(key);
    }
    public String getParameter(String key){
        return parameters.get(key);
    }
    public String getCookie(String key){
        return cookies.get(key);
    }
}
