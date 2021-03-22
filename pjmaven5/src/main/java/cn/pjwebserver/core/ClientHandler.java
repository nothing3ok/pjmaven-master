package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpRequest;

import java.io.File;
import java.io.IOException;
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
