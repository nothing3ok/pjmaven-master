package cn.pjwebserver.http;

import java.util.HashMap;
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
