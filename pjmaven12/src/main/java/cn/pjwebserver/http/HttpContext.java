package cn.pjwebserver.http;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设计这个类的目的是将多有HTTP 协议 定义的内容都放在这里
 * 这样我们无论哪个类需要用到HTTP协议的东西时，都可以来这里找到
 *
 */
public class HttpContext {
    private static final Map<String,String> MIME_MAPPING = new HashMap<>();
    static {
        //初始化
        initMimeMapping();
    }

    /**
     * 初始化MIME_MAPPING
     */
    private static void initMimeMapping(){
//        MIME_MAPPING.put("html","text/html");
//        MIME_MAPPING.put("css","text/css");
//        MIME_MAPPING.put("js","application/javascript");
//        MIME_MAPPING.put("png","image/png");
//        MIME_MAPPING.put("jpg","image/jpeg");
//        MIME_MAPPING.put("gif","image/gif");
        /**
         * 通过歇息conf/web.xml，将所有的类型初始化出来
         * 1 创建saxreader 读取conf 目录下的web。xml文件
         * 2 跟元素下所有名为<mime-mapping></mime-mapping>的子标签获取出来
         * 3 便利所有的<mime-mapping></mime-mapping>标签，并将子标签
         * <extension></extension>中间的问分作为valuev保存到mime_mapping 这个map中完成初始化
         *
         */
        try {
            SAXReader reader=new SAXReader();
            //读取到xml
            Document doc=reader.
                    read(new File("./pjmaven12/conf/web.xml"));
            //得到标签头
            Element element=doc.getRootElement();
            List<Element> f= element.elements("mime-mapping");
            for (Element element1 : f) {
                String key=element1.elementText("extension");
                String value=element1.elementText("mime-type");
                MIME_MAPPING.put(key,value);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println(MIME_MAPPING.size());
    }
    public static String getMimeType(String ext){
        return MIME_MAPPING.get(ext);
    }

    public static void main(String[] args) {

        String fileName="xxx.css";
        int index=fileName.lastIndexOf(".")+1;
        String ext=fileName.substring(index);
        String line=HttpContext.getMimeType(ext);
        System.out.println(line);
    }
}
