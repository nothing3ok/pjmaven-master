package cn.pjwebserver.core;

import cn.serverlet.HttpServlet;
import cn.serverlet.LoginServlet;
import cn.serverlet.RegServlet;

import java.util.HashMap;
import java.util.Map;

/**
 * �����������Ϣ
 */
public class ServerContext {

    /**
     * ����servlet
     * key������·��
     * value�� ���崦���Ӧҵ���servlet ʵ��
     */
    private  static Map<String, HttpServlet> SERVLET_MAPPING =new HashMap<>();
    static {

        //��ʼ�����е�Servlet
        initServletMapping();
    }
    private static void initServletMapping(){
        SERVLET_MAPPING.put("/myweb/reg",new RegServlet());
        SERVLET_MAPPING.put("/myweb/login",new LoginServlet());
    }

    /**
     * ��������·����ȡ��Ӧ��servletʵ��
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
