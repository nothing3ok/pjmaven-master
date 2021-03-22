package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpRequest;

import java.io.IOException;
import java.net.Socket;

/**
 * 处理客户端请求
 */
public class ClientHandler  implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket=socket;
    }

    public void run() {
        try {
            //1.解析请求
            //实例化请求对象，实例化过程中也是解析请求的过程
            HttpRequest request=new HttpRequest(socket);




            //2.处理请i去





            //3发送相应






        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                //断开后释放资源
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
