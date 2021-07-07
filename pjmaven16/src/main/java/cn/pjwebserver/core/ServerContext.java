package cn.pjwebserver.core;

import cn.serverlet.HttpServlet;
import cn.serverlet.LoginServlet;
import cn.serverlet.RegServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
        try {
            initServletMapping();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void initServletMapping() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        SERVLET_MAPPING.put("/myweb/reg",new RegServlet());
//        SERVLET_MAPPING.put("/myweb/login",new LoginServlet());
        /**
         * ����cong/servlets.xml
         * ������ǩ�����е�servlet ��ǩ��ȡ��
         * ������path ˯�ѵ�ֵ��Ϊkey
         * ��classname ���Ե�ֵȡ�������÷���ʵ����
         * ��Ӧ��servlet ʵ����Ϊvalue
         * ���浽serlet����mapping ���map����ɳ�ʼ��
         *
         * ע�⣺���÷������ÿ��servlet ��ʵ�����󣬷��صĶ���oject ��������Щservlet ���̳���
         * httpservlet ���н��������httpservlet ���ɣ�Ȼ����value
         * ��ʽ����map
         *
         */


        try {
            SAXReader reader=new SAXReader();
            //��ȡ��xml
            Document doc=reader.
                    read(new File("./pjmaven16/conf/servlets.xml"));
            Element root=doc.getRootElement();
            List<Element> f= root.elements("servlet");
            for (Element servletEle : f) {
                String key=servletEle.attributeValue("path");
                String className=servletEle.attributeValue("className");
                Class cla=Class.forName(className);
                //�����������ǿת  Ҫ��Ȼ�� object  ����
                HttpServlet value=(HttpServlet) cla.newInstance();
                SERVLET_MAPPING.put(key,value);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //�õ���ǩͷ







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
