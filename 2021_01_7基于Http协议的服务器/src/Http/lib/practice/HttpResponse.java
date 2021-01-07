package Http.lib.practice;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:表示一个http响应并解析
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-07 9:54
 */
public class HttpResponse {
    private String version = "Http/1.1";//http响应首行的版本号
    private int status;//http响应首行的状态码
    private String message;//http的状态码描述信息
    private Map<String,String> headers = new HashMap<>();
    private StringBuilder body = new StringBuilder();//方便一会儿进行拼接
    //当代码需要将响应写回客户端的时候就往这里面写
    private OutputStream outputStream = null;
    //用工厂方法建立httpResponse
    public static HttpResponse build(OutputStream outputStream){
        HttpResponse response = new HttpResponse();
        response.outputStream = outputStream;
        return response;
    }
    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setHeader(String key,String value){
        headers.put(key, value);
    }
    public void writeBody(String content){
        body.append(content);
    }
    //以上设置属性都是在内存中执行，需要一个方法将以上属性按照http协议规则真正写进socket中
    public void flush() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //1)写首行信息
        bufferedWriter.write(version+" "+status+" "+message+"\n");
        //2)写入header信息
        //计算Content-Length
        headers.put("Content-Length",body.toString().getBytes()+"");
        for (Map.Entry<String,String> entry : headers.entrySet()){
            bufferedWriter.write(entry.getKey()+": "+entry.getValue()+"\n");
        }
        //3)写入空行
        bufferedWriter.write("\n");
        //4)写入body
        bufferedWriter.write(body.toString());
        bufferedWriter.flush();
    }
}
