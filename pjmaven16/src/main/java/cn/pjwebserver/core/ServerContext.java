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
         * 加载cong/servlets.xml
         * 将根标签下所有的servlet 标签获取到
         * 并将其path 睡醒的值作为key
         * 将classname 属性的值取出并利用反射实例化
         * 对应的servlet 实例作为value
         * 保存到serlet——mapping 这个map中完成初始化
         *
         * 注意：利用反射加载每个servlet 并实例化后，返回的都是oject ，但是这些servlet 都继承自
         * httpservlet 所有将他们造成httpservlet 即可，然后以value
         * 形式存入map
         *
         */


        try {
            SAXReader reader=new SAXReader();
            //读取到xml
            Document doc=reader.
                    read(new File("./pjmaven16/conf/servlets.xml"));
            Element root=doc.getRootElement();
            List<Element> f= root.elements("servlet");
            for (Element servletEle : f) {
                String key=servletEle.attributeValue("path");
                String className=servletEle.attributeValue("className");
                Class cla=Class.forName(className);
                //这里进进行了强转  要不然是 object  类型
                HttpServlet value=(HttpServlet) cla.newInstance();
                SERVLET_MAPPING.put(key,value);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //得到标签头







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
