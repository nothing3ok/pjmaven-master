package cn.pjwebserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 请求对象
 * 该类类的每个实例用于表示一个http请求内容
 *
 * 一个http请求包含三部分：
 * 请求行，消息头，消息正文
 */
public class HttpRequest {
    /**
     * 请求行相关信息定义
     */
    //请求方式
    private String method;
    //请求资源的抽象路径
    private String url;
    //请求使用的http协议版本
    private String protocol;

    /**
    消息头相关信息定义
     */

    /**
     * 消息正文相关信息定义
     */


    /**
     * 和链接相关信息定义
     */
    private Socket socket;
    private InputStream in;
    /**
     * 构造方法，用来初始化请求对象
     */
    public HttpRequest(Socket socket) {

        try {
            this.socket=socket;
            this.in=socket.getInputStream();
            /**
             * 1.解析请求行
             * 2.解析消息头
             * 3.解析消息正文
             */
            parseRequestLine();
            parseHeaders();
            parseContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息请求行
     */
    private void parseRequestLine(){
        System.out.println("httprequest:解析请求行");
        try {
            //读取发过来的内容
            StringBuilder builder=new StringBuilder();
            int c1=-1,c2=-1;
            while ((c2=in.read())!=-1){
                if (c1==13&&c2==10){
                    break;}
                builder.append((char)c2);
                c1=c2;
            }
            String line =builder.toString().trim();
            System.out.println("请求行："+line);
            String[]data=line.split("\\s");
            /**
             * 这里后期可以循环接收客户端请求时会偶尔出现下标越界的情况，
             * 实际是因为空请求问题，后期解决
             */
            method=data[0];
            url=data[1];
            protocol=data[2];
            System.out.println("method:"+method);
            System.out.println("url:"+url);//这里可能会下标越界
            System.out.println("protocol:"+protocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("解析完毕");
    }

    /**
     * 解析消息头
     */
    private void parseHeaders(){}

    /**
     * 解析消息正文
     */
    private void parseContent(){}
}
