package cn.pjwebserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * �������
 * �������ÿ��ʵ�����ڱ�ʾһ��http��������
 *
 * һ��http������������֣�
 * �����У���Ϣͷ����Ϣ����
 */
public class HttpRequest {
    /**
     * �����������Ϣ����
     */
    //����ʽ
    private String method;
    //������Դ�ĳ���·��
    private String url;
    //����ʹ�õ�httpЭ��汾
    private String protocol;

    /**
    ��Ϣͷ�����Ϣ����
     */

    private Map<String,String> headers=new HashMap<String, String>();
    /**
     * ��Ϣ���������Ϣ����
     */


    /**
     * �����������Ϣ����
     */
    private Socket socket;
    private InputStream in;
    /**
     * ���췽����������ʼ���������
     */
    public HttpRequest(Socket socket) {

        try {
            this.socket=socket;
            this.in=socket.getInputStream();
            /**
             * 1.����������
             * 2.������Ϣͷ
             * 3.������Ϣ����
             */
            parseRequestLine();
            parseHeaders();
            parseContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��Ϣ������
     */
    private void parseRequestLine(){
        System.out.println("httprequest:����������");
        try {
            //��ȡ������������

            String line=readLine();
            String[]data=line.split("\\s");
            /**
             * ������ڿ���ѭ�����տͻ�������ʱ��ż�������±�Խ��������
             * ʵ������Ϊ���������⣬���ڽ��
             */
            method=data[0];
            url=data[1];
            protocol=data[2];
            System.out.println("method:"+method);
            System.out.println("url:"+url);//������ܻ��±�Խ��
            System.out.println("protocol:"+protocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("�������");
    }

    /**
     * ������Ϣͷ
     */
    private void parseHeaders(){

        System.out.println("HttpRequest:������Ϣͷ");
        /**
         * parserequestline�����Ѿ�ͨ�������������е�
         * ���������ݶ�ȡ��ϣ���ô��parseHeaders�������
         * ʱ��ͨ����������ȡ������Ӧ��������Ϣͷ�����ˣ�
         * ����˼·��
         * ˳���ȡ�������ַ�����ÿһ�ж���һ����Ϣͷ��
         * ����Ϣͷ���ա�ð�ſո񣨣� �������Ϊ�����֣��ֱ�����Ϣͷ���������Ӧ��ֵ
         * ����ÿ����Ϣͷ��������Ϊkey����Ϣͷ��ֵ��Ϊvalue
         * ���浽headers���map�м�����ɽ�������
         */

        try {
            while (true){
                String line=readLine();
                if ("".equals(line)){
                    break;
                }
                String[] data=line.split(": ");
                headers.put(data[0],data[1]);
            }
            System.out.println("headers:"+headers);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("HttpRequest:��Ϣͷ�������");
    }

    /**
     * ������Ϣ����
     */
    private void parseContent(){}

    /**
     * ͨ����Ӧ�ͻ��˵�����������ȡһ�пͻ��˷��͹������ַ�����һ������CRLF��Ϊ������
     */
    private String readLine()throws IOException{
        StringBuilder builder=new StringBuilder();
        int c1=-1,c2=-1;
        while ((c2=in.read())!=-1){
            if (c1==13&&c2==10){
                break;}
            builder.append((char)c2);
            c1=c2;
        }
        return builder.toString().trim();
    }

    /**
     * ����httprequest��Ӧ���Զ�����ṩgetfangfa
     * ��Ϊ���������ǿͻ��˷��͹����ģ����Բ���Ҫ�������Ķ���
     * ��˶���ֻ��ֻ���ļ���
     * @return
     */

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHeaders(String name) {
        return headers.get(name);
    }
}