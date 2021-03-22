package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

import javax.swing.text.html.parser.Entity;
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
            //ʵ������Ӧ����
            HttpResponse response=new HttpResponse(socket);


            //2.����������
            //��ͨ��request��ȡ�ù����������Դ�ĳ���·��
            String path=request.getUrl();
            //��webappsĿ¼�¸��ݳ���·��Ѱ��������Դ
            File file=new File("./pjmaven7/webapps"+path);
            //�ж��û��������Դ�Ƿ����
            if (file.exists()){
                System.out.println("����Դ���ҵ���");
                //��Ҫ��Ӧ����Դ���õ�response��
                response.setEntity(file);
                //��Ӧ�ͻ���
                response.flush();
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
