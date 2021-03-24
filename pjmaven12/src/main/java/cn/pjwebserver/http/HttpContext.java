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
 * ���������Ŀ���ǽ�����HTTP Э�� ��������ݶ���������
 * �������������ĸ�����Ҫ�õ�HTTPЭ��Ķ���ʱ���������������ҵ�
 *
 */
public class HttpContext {
    private static final Map<String,String> MIME_MAPPING = new HashMap<>();
    static {
        //��ʼ��
        initMimeMapping();
    }

    /**
     * ��ʼ��MIME_MAPPING
     */
    private static void initMimeMapping(){
//        MIME_MAPPING.put("html","text/html");
//        MIME_MAPPING.put("css","text/css");
//        MIME_MAPPING.put("js","application/javascript");
//        MIME_MAPPING.put("png","image/png");
//        MIME_MAPPING.put("jpg","image/jpeg");
//        MIME_MAPPING.put("gif","image/gif");
        /**
         * ͨ��ЪϢconf/web.xml�������е����ͳ�ʼ������
         * 1 ����saxreader ��ȡconf Ŀ¼�µ�web��xml�ļ�
         * 2 ��Ԫ����������Ϊ<mime-mapping></mime-mapping>���ӱ�ǩ��ȡ����
         * 3 �������е�<mime-mapping></mime-mapping>��ǩ�������ӱ�ǩ
         * <extension></extension>�м���ʷ���Ϊvaluev���浽mime_mapping ���map����ɳ�ʼ��
         *
         */
        try {
            SAXReader reader=new SAXReader();
            //��ȡ��xml
            Document doc=reader.
                    read(new File("./pjmaven12/conf/web.xml"));
            //�õ���ǩͷ
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
