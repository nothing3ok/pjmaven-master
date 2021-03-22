package cn.pjwebserver.http;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ��Ӧ����
 * �����ÿ��ʵ�����ڱ�ʾ����˷��͸��ͻ��˵�http��Ӧ����
 */

public class HttpResponse {
    /**
     * ״̬���������Ϣ����
     */
    private int statusCode=200;

    private String statusReason="OK";

    /**
     * ��Ӧͷ�����Ϣ����
     */
//key:��Ӧͷ value����Ӧֵ
    private Map<String,String> headers=new HashMap<>();

    /**
     * ��Ӧͷ������Ϣ����
     */
    //��Ӧ���ĵ�ʵ���ļ�
    private File entity;


    private Socket socket;
    private OutputStream out;

    /**
     * ʵ������������ͬʱ��socket����
     * @param socket
     */
    public HttpResponse(Socket socket) {


        try {
            this.socket=socket;
            this.out=socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * ���ڽ���ǰ��Ӧ�����������һ����׼��HTTP��Ӧ��ʽ���͸��ͻ���
     */

    public  void flush(){
        //˳����
        sendStatusLine();
        sendHeaders();
        sendContent();
    }
    /**
     * ��Ӧ�����������Ϣ����
     */
    /**
     * ����״̬��
     */
    private  void sendStatusLine(){

        try {
            //����״̬��
            String line="HTTP/1.1"+" "+statusCode+" "+statusReason;
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ������Ӧͷ
     */
    private void sendHeaders(){

        try {

            Set<Map.Entry<String,String>>entrySet=headers.entrySet();
            for (Map.Entry<String, String> e: headers.entrySet()) {
                String name=e.getKey();
                String value=e.getValue();
                String line=name+": "+value;
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
            }
            //��������CRLF��ʾ��Ӧͷ���ַ������
            out.write(13);
            out.write(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ������Ӧ����
     */
    private  void sendContent(){
        try (FileInputStream fis=new FileInputStream(entity);
        ){
            //������Ӧ���ģ��û�ʵ���������Դ���ݣ�
            int len=-1;
            byte[] data=new byte[1024*10];
            while ((len=fis.read(data))!=-1){
                out.write(data,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getEntity() {
        return entity;
    }

    /**
     * ��������ʵ���ļ����õ�response��
     * ���õ�ͬʱ���Զ����ݸ��ļ���Ӷ�Ӧ��������Ӧͷ
     * content-type ��content-length
     * @param entity
     */
    public void setEntity(File entity) {
        this.entity = entity;
        //����������Դ��ʵ�����ͣ�����content-typeͷ
        String fileName=entity.getName();
        int index=fileName.lastIndexOf(".")+1;
        String ext=fileName.substring(index);
        String line= HttpContext.getMimeType(ext);
        putHeader("Content-Type",line);
        putHeader("Content-Length",entity.length()+" ");
    }



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public void putHeader(String name,String value) {
        this.headers.put(name,value);
    }

    public String getHeaders(String name) {
        return this.headers.get(name);
    }
}
