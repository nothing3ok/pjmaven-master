package cn.pjwebserver.core;

import cn.pjwebserver.http.EmptyRequestException;
import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;
import cn.serverlet.LoginServlet;
import cn.serverlet.RegServlet;

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
            String path=request.getRequestURI();
            //�����жϸ�����·���Ƿ�Ϊ�����һ��ҵ��
            if ("/myweb/reg".equals(path)){
                //����ҵ��
                RegServlet servlet=new RegServlet();
                servlet.Servlet(request,response);
            }else if ("/myweb/login".equals(path)){
                LoginServlet service=new LoginServlet();
                service.Servlet(request,response);
            }
            else {
                //��webappsĿ¼�¸��ݳ���·��Ѱ��������Դ
                File file=new File("./pjmaven13/webapps"+path);
                //�ж��û��������Դ�Ƿ����
                if (file.exists()){
                    System.out.println("����Դ���ҵ�!");
                    //��Ҫ��Ӧ����Դ���õ�response��
                    response.setEntity(file);

                    //��Ӧ�ͻ���
                    //������Ӧ���ݷ������
                }else {
                    System.out.println("����Դ������");
                    //��Ӧ404ҳ��
                    response.setEntity(new File("./pjmaven13/webapps/root/404.html"));
                    response.setStatusCode(404);
                    response.setStatusReason("NOT FOUND!");
                }
            }

            //3������Ӧ
            response.flush();

        }
        /**
         * ���ﵥ������������쳣
         * ���client handler��һ��ʼʵ�����������httprequestʱ���ù��췽���׳���
         * �������쳣����ôֱ�ӻ�����catch��������͵��ں����ˣ�clienthandler ����Ӧ�ã�
         * �еĴ����������Ӧ�ͻ��˵Ĳ����ˡ�
         */
        catch (EmptyRequestException e){
            System.out.println("������.");
        }
        catch (Exception e) {
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
