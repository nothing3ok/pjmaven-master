package cn.pjwebserver.http;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 响应对象
 * 该类的每个实力用于表示服务端发送给客户端的http响应内容
 */

public class HttpResponse {
    /**
     * 状态行相关新信息定义
     */
    /**
     * 响应头关新信息定义
     */
    //响应正文的实体文件
    private File entity;


    private Socket socket;
    private OutputStream out;

    /**
     * 实例化请求对象的同时将socket传入
     * @param socket
     */
    public HttpResponse(Socket socket) {


        try {
            this.socket=socket;
            this.out=socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用于将当前响应对象的内容以一个标准的HTTP响应格式发送给客户端
     */

    public  void flush(){
        //顺序发送
        sendStatusLine();
        sendHeaders();
        sendContent();
    }
    /**
     * 相应正文相关新信息定义
     */
    /**
     * 发送状态行
     */
    private  void sendStatusLine(){

        try {
            //发送状态行
            String line="HTTP/1.1 200 OK";
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应头
     */
    private  void sendHeaders(){

        try {
            //发送响应头
            String line="Content-Type: text/html";
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);

            line="Content-Length: "+entity.length();
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);
            //单独发送CRLF表示响应头部分发送完毕
            out.write(13);
            out.write(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送响应正文
     */
    private  void sendContent(){
        try (FileInputStream fis=new FileInputStream(entity);
        ){
            //发送相应正文（用户实际请求的资源数据）
            int len=-1;
            byte[] data=new byte[1024*10];
            while ((len=fis.read(data))!=-1){
                out.write(data,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getEntity() {
        return entity;
    }

    public void setEntity(File entity) {
        this.entity = entity;
    }
}
