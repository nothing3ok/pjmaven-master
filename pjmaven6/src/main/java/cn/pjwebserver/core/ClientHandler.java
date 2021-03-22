package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
                OutputStream out=socket.getOutputStream();
                //发送状态行
                String line="HTTP/1.1 200 OK";
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
                //发送响应头
                line="Content-Type: text/html";
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);

                line="Content-Length: "+file.length();
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
                //单独发送CRLF表示响应头部分发送完毕
                out.write(13);
                out.write(10);
                //发送相应正文（用户实际请求的资源数据）
                FileInputStream fis=new FileInputStream(file);
                int len=-1;
                byte[] data=new byte[1024*10];
                while ((len=fis.read(data))!=-1){
                    out.write(data,0,len);
                }
                //所有相应内容发送完毕


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
