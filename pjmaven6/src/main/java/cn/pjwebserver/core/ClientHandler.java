package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ����ͻ�������
 */
public class ClientHandler  implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket=socket;
    }

    public void run() {
        try {
            //1.��������
            //ʵ�����������ʵ����������Ҳ�ǽ�������Ĺ���
            HttpRequest request=new HttpRequest(socket);
            //2.����������
            //��ͨ��request��ȡ�ù����������Դ�ĳ���·��
            String path=request.getUrl();
            //��webappsĿ¼�¸��ݳ���·��Ѱ��������Դ
            File file=new File("./pjmaven5/webapps"+path);
            //�ж��û��������Դ�Ƿ����
            if (file.exists()){
                System.out.println("����Դ���ҵ���");
                OutputStream out=socket.getOutputStream();
                //����״̬��
                String line="HTTP/1.1 200 OK";
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
                //������Ӧͷ
                line="Content-Type: text/html";
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);

                line="Content-Length: "+file.length();
                out.write(line.getBytes("ISO8859-1"));
                out.write(13);
                out.write(10);
                //��������CRLF��ʾ��Ӧͷ���ַ������
                out.write(13);
                out.write(10);
                //������Ӧ���ģ��û�ʵ���������Դ���ݣ�
                FileInputStream fis=new FileInputStream(file);
                int len=-1;
                byte[] data=new byte[1024*10];
                while ((len=fis.read(data))!=-1){
                    out.write(data,0,len);
                }
                //������Ӧ���ݷ������


            }else {
                System.out.println("����Դ������");
            }





            //3������Ӧ






        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                //�Ͽ����ͷ���Դ
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
