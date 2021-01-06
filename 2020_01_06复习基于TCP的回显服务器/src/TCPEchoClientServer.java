import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-06 9:00
 */
public class TCPEchoClientServer {
    private Socket socket = null;

    public TCPEchoClientServer(String serverIp,int serverPort) throws IOException {
        socket = new Socket(serverIp,serverPort);
    }
    public void start(){
        System.out.println("客户端启动");
        Scanner scanner = new Scanner(System.in);
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ){
            while(true){
                System.out.println("->请输入请求");
                String request = scanner.nextLine();
                if ("exit".equals(request)){
                    System.out.println("退出");
                    break;
                }
                bufferedWriter.write(request+"\n");
                bufferedWriter.flush();
                String response = bufferedReader.readLine();
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPEchoClientServer client = new TCPEchoClientServer("127.0.0.1",9090);
        client.start();
    }
}
