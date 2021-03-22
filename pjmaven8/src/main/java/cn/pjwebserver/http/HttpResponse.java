package cn.pjwebserver.http;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

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
    private  void sendHeaders(){

        try {
            //������Ӧͷ
            String line="Content-Type: text/html";
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);

            line="Content-Length: "+entity.length();
            out.write(line.getBytes("ISO8859-1"));
            out.write(13);
            out.write(10);
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

    public void setEntity(File entity) {
        this.entity = entity;
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
}
