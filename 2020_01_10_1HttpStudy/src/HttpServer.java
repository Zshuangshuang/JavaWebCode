import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZouDouble
 * Description:
 * 1)支持返回一个静态页面
 * 2)解析处理cookie(将cookie处理解析成为一个键值对)
 * 3)解析处理body(将body处理解析成一个键值对)
 * 4)实现简单的会话功能(实现简单的session)
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-09 14:39
 */
public class HttpServer {
    static class User{
        public String username;
        public int age;
        public String school;
    }
    private ServerSocket serverSocket = null;
    //Session指的是同一个用户的一组访问服务器操作
    private HashMap<String,User> sessions = new HashMap<>();

    public HttpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    public void start() throws IOException {
        System.out.println("服务器启动");
        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true){
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    process(clientSocket);
                }
            });
        }
    }
    public void process(Socket clientSocket)  {
        try {
            //接收请求并解析
            HttpRequest request = HttpRequest.build(clientSocket.getInputStream());
            HttpResponse response = HttpResponse.build(clientSocket.getOutputStream());
            //根据请求计算响应
            if ("GET".equalsIgnoreCase(request.getMethod())){
                doGET(request,response);
            }else if ("POST".equalsIgnoreCase(request.getMethod())){
                doPOST(request,response);
            }else {
                response.setStatus(405);
                response.setMessage("Method Not Allowed");
            }
            //将响应写回客户端
            response.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void doGET(HttpRequest request, HttpResponse response) throws IOException {
        //1)能够支持返回一个html文件
        if (request.getUrl().startsWith("/index.html")){
            String sessionId = request.getCookie("sessionId");
            User user = sessions.get(sessionId);
            if (sessionId == null || user == null) {
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeader("Content-Type","text/html; charset=utf-8");
                InputStream inputStream =  HttpServer.class.getClassLoader().getResourceAsStream("index.html");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    response.writeBody(line+"\n");
                }
                bufferedReader.close();
            }else {
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeader("Content-Type","text/html; charset=utf-8");
                response.writeBody("<html>");
                response.writeBody("<div>"+"你已登录~无需重复登录 用户名为："+user.username+"</div>");
                response.writeBody("<div>"+user.age+"</div>");
                response.writeBody("<div>"+user.school+"</div>");
                response.writeBody("</html>");
            }
        }
    }


    private void doPOST(HttpRequest request, HttpResponse response) throws IOException {
        //实现/login的处理
        if (request.getUrl().startsWith("/login")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
           /* System.out.println("username:"+username);
            System.out.println("password:"+password);*/
            //登录逻辑需要判断用户名密码是否正确
            if ("aaa".equals(username) && "123".equals(password)){
                response.setStatus(200);
                response.setMessage("OK");
                response.setHeader("Content-Type","text/html; charset=utf-8");
                //response.setHeader("Set-Cookie","username="+username);
                //生成随机且唯一的一个字符串
                String sessionId = UUID.randomUUID().toString();
                User user = new User();
                user.username = "aaa";
                user.age = 20;
                user.school = "西安工业大学";
                sessions.put(sessionId,user);
                response.setHeader("Set-Cookie","sessionId="+sessionId);
                response.writeBody("<html>");
                response.writeBody("<div>欢迎你"+username+"</div>");
                response.writeBody("</html>");
            }else {
                //登陆失败
                response.setStatus(403);
                response.setMessage("Forbidden");
                response.setHeader("Content-Type","text/html; charset=utf-8");
                response.writeBody("<html>");
                response.writeBody("<div>登陆失败</div>");
                response.writeBody("</html>");
            }
        }

    }

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(9090);
        server.start();
    }
}
