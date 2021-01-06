package src.libs.code;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:表示http响应
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 20:29
 */
public class HttpResponse {
    private String version = "Http/1.1";
    private int status;
    private String message;
    private Map<String,String> headers = new HashMap<>();
    private StringBuilder body = new StringBuilder();//方便一会儿进行拼接
    private OutputStream outputStream = null;
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
    public void setHeaders(String key,String value){
        headers.put(key, value);
    }
    public void writeBody(String content){
        body.append(content);
    }
    public void flush() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //构造首行
        bufferedWriter.write(version+" "+status+" "+message+"\n");
        //计算content-length
        headers.put("Content-Length",body.toString().getBytes().length+" ");
        //写入header
        for (Map.Entry<String,String> entry : headers.entrySet()){
            bufferedWriter.write(entry.getKey()+": "+entry.getValue()+"\n");
        }
        //写入空行
        bufferedWriter.write("\n");
        //写入body
        bufferedWriter.write(body.toString());
    }
}
