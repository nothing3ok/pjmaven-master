package cn.pjwebserver.core;

import cn.serverlet.HttpServlet;
import cn.serverlet.LoginServlet;
import cn.serverlet.RegServlet;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务端配置信息
 */
public class ServerContext {

    /**
     * 所有servlet
     * key：请求路径
     * value： 具体处理对应业务的servlet 实例
     */
    private  static Map<String, HttpServlet> SERVLET_MAPPING =new HashMap<>();
    static {

        //初始化所有的Servlet
        initServletMapping();
    }
    private static void initServletMapping(){
        SERVLET_MAPPING.put("/myweb/reg",new RegServlet());
        SERVLET_MAPPING.put("/myweb/login",new LoginServlet());
    }

    /**
     * 根据请求路径获取对应是servlet实例
     * @param path
     * @return
     */
    public static HttpServlet getServlet(String path){
        return  SERVLET_MAPPING.get(path);
    }

    public static void main(String[] args) {
        HttpServlet servlet=getServlet("/myweb/login");
        System.out.println(servlet);
    }
}
