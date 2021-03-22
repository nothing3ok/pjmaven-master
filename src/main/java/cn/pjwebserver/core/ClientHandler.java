package cn.pjwebserver.core;

import java.io.IOException;
import java.io.InputStream;
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
            //读取发过来的内容
            InputStream in=socket.getInputStream();
            int d=-1;
            while ((d=in.read())!=-1){
                System.out.print((char)d);
            }
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
