package cn.serverlet;

import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.RandomAccess;

/**
 * ע��ҵ��
 */
public class RegServlet {
    public void Servlet(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("RegServlet:��ʼע���û�ҵ��");

        /**
         * 1:ͨ��request ��ȡ�û���פ��ҳ���������ע����Ϣ
         * 2 �����û���Ϣ���뵽�ļ�user��dat�б�������
         * 3 ��Ӧ�ͻ���ע��ɹ�ҳ��
         */
        //1
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int age= Integer.parseInt(request.getParameter("age"));
        String sex=request.getParameter("sex");
        System.out.println(username+","+password+","+age+","+sex);

        /**
         * 2
         * ���û���Ϣд���ļ�����ȥ
         */
        try(RandomAccessFile raf=new RandomAccessFile("user.dat","rw")) {
          //��ָ���ƶ����ļ�ĩβ
            raf.seek(raf.length());
            //дͬ����
            byte[] data=username.getBytes("UTF-8");
            data= Arrays.copyOf(data,32);
            raf.write(data);//д��32�ֽ�
            //д����
            data=password.getBytes("UTF-8");
            data=Arrays.copyOf(data,32);
            raf.write(data);
            //д����
            data=sex.getBytes("UTF-8");
            data=Arrays.copyOf(data,32);
            raf.write(data);
            //д����
            raf.writeInt(age);
            //3 response ,��Ӧע��ɹ�
            response.setEntity(new File("./pjmaven12/webapps/myweb/reg_success.html"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("RegServlet:ע���û�ҵ�����");
    }
}
