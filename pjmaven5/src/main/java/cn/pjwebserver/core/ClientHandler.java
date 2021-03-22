package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpRequest;

import java.io.File;
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




            //2.处理请请求
            //先通过request获取用过户请求的资源的抽象路径
            String path=request.getUrl();
            //从webapps目录下根据抽象路径寻找请求资源
            File file=new File("./pjmaven5/webapps"+path);
            //判断用户请求的资源是否存在
            if (file.exists()){
                System.out.println("该资源已找到！");
            }else {
                System.out.println("该资源不存在");
            }





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
