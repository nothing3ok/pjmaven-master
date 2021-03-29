package cn.serverlet;

import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginServlet {
    public  void Servlet(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("登入");
        //获取对应的值
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int age= Integer.parseInt(request.getParameter("age"));
        String sex=request.getParameter("sex");

        //读取文件中的值


        try (RandomAccessFile raf =new RandomAccessFile("user.dat","r");) {
            boolean check =false;
            for (int i = 0; i < raf.length()/100; i++) {
                raf.seek(i*100);
                byte[] data=new byte[32];
                raf.read();
                String name= new String(data,"UTF-8");
                if (name.equals(username)){
                    raf.read(data);
                    String pwd=new String(data,"UTF-8");
                    if (pwd.equals(password)){
                    //登入成功
                        check=true;
                        break;
                    }

                    if (check){
                        response.setEntity(new File("./pjmaven13/webapps/myweb/login_success.html"));
                    }else {
                        response.setEntity(new File("./pjmaven13/webapps/myweb/login_fail.html"));
                    }
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登入成功");
    }
}
