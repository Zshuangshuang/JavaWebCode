package http.practice.www;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:负责解析请求和构造请求
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-08 9:12
 */
public class HttpRequest {
    private String method;//请求方法
    private String url;//网址
    private String version;//版本号
    private Map<String,String> headers = new HashMap<>();//header
    private Map<String,String> parameters = new HashMap<>();//解析的url参数
    //使用工厂模式构造请求
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
            String parameters = request.url.substring(pos+1);
            parseKv(parameters,request.parameters);
        }
        //解析header
        String line = "";
        while((line = bufferedReader.readLine()) != null && line.length() != 0){
            String[] headerTokens = line.split(": ");
            request.headers.put(headerTokens[0], headerTokens[1]);
        }
       return request;
    }

    private static void parseKv(String input,Map<String,String> output) {
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
    public String getHeader(String key){
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
