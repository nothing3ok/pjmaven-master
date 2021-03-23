package cn.pjwebserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
    //url中的请求部分，“？”左侧内容
    private String requestURI;
    //url中的请求部分，“？”右侧内容
    private String queryString;
    //每一组参数key：参数名，value：参数值
    private Map<String,String>parameters =new HashMap<>();
    /**
    消息头相关信息定义
     */

    private Map<String,String> headers=new HashMap<String, String>();
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
            String line=readLine();
            String[]data=line.split("\\s");
            /**
             * 这里后期可以循环接收客户端请求时会偶尔出现下标越界的情况，
             * 实际是因为空请求问题，后期解决
             */
            method=data[0];
            url=data[1];
            parseURL();//进一步解析url
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
     * 进一步解析请求行中的url部分
     * 因为一个url可能有两种情况：包含参数或不含有参数
     * 如果含有参数要对参数进行解析
     */
    private void parseURL(){

        System.out.println("HttpRequest:进一步解析url");
        /**
         * url有两种情况：
         * 1：不含有参数，如：/myweb/index.html
         * 对于这种情况，直接将url的设置到属性requestURI上即可
         *
         * 2 含有参数，如：/myweb/reg？username=xx&password=xx...
         * 对于这种情况，我们首先向url按照“？”拆分为两部分
         * 第一种部分为参数请求部分，赋值给requestURI
         * 第二部分为参数部分，赋值给queryString
         * 然后在对参数部分进一步拆分：
         * 首先按照，”&“拆分出每一组参数，然后每一组参数在按照”=“
         * 拆分为两部分，分别是参数名的参数值，在将他们以key，value 存入到
         * parameters这个Map类型属性完成解析工作
         */
        this.url.split("/?");
        //contains方法比较 或者indexOf方法比较
        if (url.indexOf("?")==-1){
            this.requestURI=url;
        }else {
            String[] data=url.split("\\?");
            this.requestURI=data[0];
            if (data.length>1){
                this.queryString=data[1];
                //进步拆分 得到每个属性对应的值
               String[] dats= queryString.split("&");
                for (String dat : dats) {
                    //按照=拆分 得到 每个属性  和 值
                    String [] arr=dat.split("=");
                    if (arr.length>1){
                        if (("username".equals(arr[0])&&arr[1]!=null)||("password".equals(arr[0])&&arr[1]!=null)){
                            this.parameters.put(arr[0],arr[1]);
                        }
                    }
                    else if(("username".equals(arr[0]))||("password".equals(arr[0]))) {
                        System.out.println("名字没有输入");
                        return;
                    }
                    else {
                        this.parameters.put(arr[0],null);
                    }
                }
            }

        }




        System.out.println("RequestURI:"+requestURI);
        System.out.println("queryString:"+queryString);
        System.out.println("parameters:"+parameters);
        System.out.println("HttpRequest:解析url部分完毕");

    }

    /**
     * 解析消息头
     */
    private void parseHeaders(){

        System.out.println("HttpRequest:解析消息头");
        /**
         * parserequestline方法已经通过输入流请求中的
         * 请求行内容读取完毕，那么到parseHeaders这个方法
         * 时再通过输入流读取的内容应当就是消息头部分了，
         * 解析思路：
         * 顺序读取若干行字符串，每一行都是一个消息头。
         * 将消息头按照“冒号空格（： ）”拆分为两部分，分别是消息头的名字与对应的值
         * 并将每个消息头的名字作为key，消息头的值作为value
         * 保存到headers这个map中即可完成解析工作
         */

        try {
            while (true){
                String line=readLine();
                if ("".equals(line)){
                    break;
                }
                String[] data=line.split(": ");
                headers.put(data[0],data[1]);
            }
            System.out.println("headers:"+headers);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("HttpRequest:消息头解析完毕");
    }

    /**
     * 解析消息正文
     */
    private void parseContent(){}

    /**
     * 通过对应客户端的输入流，读取一行客户端发送过来的字符串，一行是以CRLF作为结束的
     */
    private String readLine()throws IOException{
        StringBuilder builder=new StringBuilder();
        int c1=-1,c2=-1;
        while ((c2=in.read())!=-1){
            if (c1==13&&c2==10){
                break;}
            builder.append((char)c2);
            c1=c2;
        }
        return builder.toString().trim();
    }

    /**
     * 这里httprequest对应属性对外仅提供getfangfa
     * 因为请求内容是客户端发送过来的，所以不需要做其他改动，
     * 因此对外只是只读的即可
     * @return
     */

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHeaders(String name) {
        return headers.get(name);
    }
}
