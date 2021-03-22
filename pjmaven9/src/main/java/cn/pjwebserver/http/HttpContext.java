package cn.pjwebserver.http;

import java.util.HashMap;
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
        MIME_MAPPING.put("html","text/html");
        MIME_MAPPING.put("css","text/css");
        MIME_MAPPING.put("js","application/javascript");
        MIME_MAPPING.put("png","image/png");
        MIME_MAPPING.put("jpg","image/jpeg");
        MIME_MAPPING.put("gif","image/gif");
    }
    public static String getMimeType(String ext){
        return MIME_MAPPING.get(ext);
    }

    public static void main(String[] args) {

        String fileName="xxx.png";
        int index=fileName.lastIndexOf(".")+1;
        String ext=fileName.substring(index);
        String line=HttpContext.getMimeType(ext);
        System.out.println(line);
    }
}
