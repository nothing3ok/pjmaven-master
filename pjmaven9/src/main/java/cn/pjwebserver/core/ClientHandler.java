package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpContext;
import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

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
            //实例化响应对象
            HttpResponse response=new HttpResponse(socket);


            //2.处理请请求
            //先通过request获取用过户请求的资源的抽象路径
            String path=request.getUrl();
            //从webapps目录下根据抽象路径寻找请求资源
            File file=new File("./pjmaven9/webapps"+path);
            //判断用户请求的资源是否存在
            if (file.exists()){
                System.out.println("该资源已找到！");
                //将要响应的资源设置到response中
                response.setEntity(file);
                //根据请求资源的实际类型，设置content-type头
                String fileName=file.getName();
                int index=fileName.lastIndexOf(".")+1;
                String ext=fileName.substring(index);
                String line= HttpContext.getMimeType(ext);
                response.putHeader("Content-Type",line);
                response.putHeader("Content-Length",file.length()+" ");
                //响应客户端
                //所有相应内容发送完毕
            }else {
                System.out.println("该资源不存在");
                //响应404页面
                response.setEntity(new File("./pjmaven9/webapps/root/404.html"));
                //对应的设置响应头content-type 和content-length
                response.putHeader("Content-Type","text/html");
                response.putHeader("Content-Length",file.length()+" ");


                response.setStatusCode(404);

                response.setStatusReason("NOT FOUND!");
            }

            //3发送相应
            response.flush();

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
