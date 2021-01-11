import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-11 9:05
 */
public class HttpResponse {
    private String version = "HTTP/1.1";//http响应首行的版本号
    private int status;//http响应首行的状态码
    private String message;
    private Map<String,String> headers = new HashMap<>();
    private StringBuilder body = new StringBuilder();
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

    public void writeBody(String content) {
        body.append(content);
    }

    public void setHeaders(String key,String value) {
        headers.put(key,value);
    }
    public void flush() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(version+" "+status+" "+message+"\n");
        headers.put("Content-Length",body.toString().getBytes().length+"");
        for (Map.Entry<String,String> entry : headers.entrySet()){
            bufferedWriter.write(entry.getKey()+": "+entry.getValue()+"\n");
        }
        bufferedWriter.write("\n");
        bufferedWriter.write(body.toString());
        bufferedWriter.flush();
    }
}
