import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-07 15:24
 */
public class HttpResponse {
    private String version = "http/1.1";//http响应的版本号
    private int status;//http响应的状态码
    private String message;//http响应的状态描述符
    private Map<String,String> headers = new HashMap<>();//http响应header
    private StringBuilder body = new StringBuilder();
    private OutputStream outputStream = null;
    //用工厂模式构造建立响应
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
//以上属性都是在内存中执行，需要一个方法把这些属性真正写进socket中
    public void flush() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        //1)写入首行信息
        bufferedWriter.write(version+" "+status+" "+message+"\n");
        //2)写入header信息
        //①计算content-length
        headers.put("Content-Length",body.toString().getBytes().length+" ");
        //写入头部信息
        for (Map.Entry<String,String> entry : headers.entrySet()){
            bufferedWriter.write(entry.getKey()+": "+entry.getValue()+"\n");
        }
        //写入空行
        bufferedWriter.write("\n");
        //写入body
        bufferedWriter.write(body.toString());
        bufferedWriter.flush();
    }
}
