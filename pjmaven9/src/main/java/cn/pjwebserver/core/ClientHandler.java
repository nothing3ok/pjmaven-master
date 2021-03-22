package cn.pjwebserver.core;

import cn.pjwebserver.http.HttpContext;
import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

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
            //ʵ������Ӧ����
            HttpResponse response=new HttpResponse(socket);


            //2.����������
            //��ͨ��request��ȡ�ù����������Դ�ĳ���·��
            String path=request.getUrl();
            //��webappsĿ¼�¸��ݳ���·��Ѱ��������Դ
            File file=new File("./pjmaven9/webapps"+path);
            //�ж��û��������Դ�Ƿ����
            if (file.exists()){
                System.out.println("����Դ���ҵ���");
                //��Ҫ��Ӧ����Դ���õ�response��
                response.setEntity(file);
                //����������Դ��ʵ�����ͣ�����content-typeͷ
                String fileName=file.getName();
                int index=fileName.lastIndexOf(".")+1;
                String ext=fileName.substring(index);
                String line= HttpContext.getMimeType(ext);
                response.putHeader("Content-Type",line);
                response.putHeader("Content-Length",file.length()+" ");
                //��Ӧ�ͻ���
                //������Ӧ���ݷ������
            }else {
                System.out.println("����Դ������");
                //��Ӧ404ҳ��
                response.setEntity(new File("./pjmaven9/webapps/root/404.html"));
                //��Ӧ��������Ӧͷcontent-type ��content-length
                response.putHeader("Content-Type","text/html");
                response.putHeader("Content-Length",file.length()+" ");


                response.setStatusCode(404);

                response.setStatusReason("NOT FOUND!");
            }

            //3������Ӧ
            response.flush();

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
