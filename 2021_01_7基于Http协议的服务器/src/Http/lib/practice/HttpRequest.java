package Http.lib.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:表示一个http请求并解析
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-07 9:54
 */
public class HttpRequest {
    private String method;//表示http协议首行中的请求方法
    private String url;//表示http协议首行中的url
    private String version;//表示http首行中的版本号
    private Map<String,String> headers = new HashMap<>();//表示http请求中的头部
    //url形如：index.html?name=a&password=123
    private Map<String,String > parameters = new HashMap<>();//表示解析url中的参数
    //构造请求,使用工厂模式
    //此处的参数inputStream是从socket参数中获取的
    public static HttpRequest build(InputStream inputStream) throws IOException {
        //此处没有用try()包裹bufferedReader，原因是这里一旦使用就会导致build方法执行后，流对象就会被关闭
        //会影响clientSocket对象的状态
        //等请求执行完毕后,最后在统一关闭
        HttpRequest request = new HttpRequest();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //1.解析首行
        String firstLine = bufferedReader.readLine();
        String[] firstLineTokens = firstLine.split(" ");
        request.method = firstLineTokens[0];
        request.url = firstLineTokens[1];
        request.version = firstLineTokens[2];
        //2.解析url中的参数
        //先要找到url的参数范围,首先看看url中是否存在?，如果没有?说明不带参数，也就不用继续找了
        int pos = request.url.indexOf("?");
        if (pos != -1){
            //url形如：index.html?name=a&password=123
            //找到url参数的范围
            //pos代表问号的位置，问号的下一个就是url参数的位置
            //parameters相当于name=a&password=123
            String parameters = request.url.substring(pos+1);
            parseKv(parameters,request.parameters);
        }
        //3.解析header
        String line = "";
        while((line = bufferedReader.readLine()) != null & line.length() != 0){
            String[] headersToken = line.split(": ");
            request.headers.put(headersToken[0], headersToken[1]);
        }
        //4.解析body(暂不考虑)
        return request;
    }

    private static void parseKv(String input, Map<String, String> output) {
        //input就是url中的参数name=a&password=123,output是url中的键值对
        //这个方法的目的是将input中的键值对获取完毕，放入output中去
        //1)将url按照&切分得到name=a，password=123
        String[] kvTokens = input.split("&");
        //2)将name=a，password=123按照=切分得到->name a,password b
        for (String kv:kvTokens){
            String[] result = kv.split("=");
            //3)将name a,password b按照键和值放入output中去
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
