package cn.serverlet;

import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginServlet extends HttpServlet {
    public  void Servlet(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("����");
        //��ȡ��Ӧ��ֵ
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //int age= Integer.parseInt(request.getParameter("age"));
        //String sex=request.getParameter("sex");

        //��ȡ�ļ��е�ֵ


        try (RandomAccessFile raf =new RandomAccessFile("user.dat","r");) {
            boolean check =false;
            for (int i = 0; i < raf.length()/100; i++) {
                //�Ƚ�ָ���ƶ���������¼��ʼλ��
                raf.seek(i*100);
                //��ȡ�û���
                byte[] data=new byte[32];
                raf.read(data);
                String name= new String(data,"UTF-8").trim();
                //֪��Ϊ���û�
                if (name.equals(username)){
                    //��ȡ����
                    raf.read(data);
                    String pwd=new String(data,"UTF-8").trim();
                    if (pwd.equals(password)){
                    //����ɹ�
                        check=true;
                        break;
                    }
                }
            }
            if (check){
                response.setEntity(new File("./pjmaven15/webapps/myweb/login_success.html"));
            }else {
                response.setEntity(new File("./pjmaven15/webapps/myweb/login_fail.html"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("����ɹ�");
    }
}