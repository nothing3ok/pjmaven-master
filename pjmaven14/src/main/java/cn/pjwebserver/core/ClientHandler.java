package cn.pjwebserver.core;

import cn.pjwebserver.http.EmptyRequestException;
import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;
import cn.serverlet.LoginServlet;
import cn.serverlet.RegServlet;

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
            String path=request.getRequestURI();
            //首先判断该请求路径是否为请求的一个业务
            if ("/myweb/reg".equals(path)){
                //请求业务
                RegServlet servlet=new RegServlet();
                servlet.Servlet(request,response);
            }else if ("/myweb/login".equals(path)){
                LoginServlet service=new LoginServlet();
                service.Servlet(request,response);
            }
            else {
                //从webapps目录下根据抽象路径寻找请求资源
                File file=new File("./pjmaven13/webapps"+path);
                //判断用户请求的资源是否存在
                if (file.exists()){
                    System.out.println("该资源已找到!");
                    //将要响应的资源设置到response中
                    response.setEntity(file);

                    //响应客户端
                    //所有相应内容发送完毕
                }else {
                    System.out.println("该资源不存在");
                    //响应404页面
                    response.setEntity(new File("./pjmaven13/webapps/root/404.html"));
                    response.setStatusCode(404);
                    response.setStatusReason("NOT FOUND!");
                }
            }

            //3发送相应
            response.flush();

        }
        /**
         * 这里单独捕获空请求异常
         * 如果client handler在一开始实例化请求对象httprequest时，该构造方法抛出了
         * 空请求异常，那么直接会跳到catch这里，这样就等于忽略了，clienthandler 后续应该，
         * 有的处理请求和相应客户端的操作了。
         */
        catch (EmptyRequestException e){
            System.out.println("空请求.");
        }
        catch (Exception e) {
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
