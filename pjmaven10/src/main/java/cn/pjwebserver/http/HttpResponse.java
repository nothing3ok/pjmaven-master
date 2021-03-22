package cn.pjwebserver.http;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 响应对象
 * 该类的每个实力用于表示服务端发送给客户端的http响应内容
 */

public class HttpResponse {
    /**
     * 状态行相关新信息定义
     */
    private int statusCode=200;

    private String statusReason="OK";

    /**
     * 响应头相关信息定义
     */
//key:响应头 value：响应值
    private Map<String,String> headers=new HashMap<>();

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
            String line="HTTP/1.1"+" "+statusCode+" "+statusReason;
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
    private void sendHeaders(){

        try {

            Set<Map.Entry<String,String>>entrySet=headers.entrySet();
            for (Map.Entry<String, String> e: headers.entrySet()) {
                String name=e.getKey();
                String value=e.getValue();
                String line=name+": "+value;
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
            }
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

    /**
     * 将给定的实体文件设置到response中
     * 设置的同时会自动根据该文件添加对应的两个响应头
     * content-type 与content-length
     * @param entity
     */
    public void setEntity(File entity) {
        this.entity = entity;
        //根据请求资源的实际类型，设置content-type头
        String fileName=entity.getName();
        int index=fileName.lastIndexOf(".")+1;
        String ext=fileName.substring(index);
        String line= HttpContext.getMimeType(ext);
        putHeader("Content-Type",line);
        putHeader("Content-Length",entity.length()+" ");
    }



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public void putHeader(String name,String value) {
        this.headers.put(name,value);
    }

    public String getHeaders(String name) {
        return this.headers.get(name);
    }
}
