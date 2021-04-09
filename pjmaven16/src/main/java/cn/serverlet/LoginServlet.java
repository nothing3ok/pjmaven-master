package cn.serverlet;

import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginServlet extends HttpServlet {
    public  void service(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("登入");
        //获取对应的值
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //int age= Integer.parseInt(request.getParameter("age"));
        //String sex=request.getParameter("sex");

        //读取文件中的值


        try (RandomAccessFile raf =new RandomAccessFile("user.dat","r");) {
            boolean check =false;
            for (int i = 0; i < raf.length()/100; i++) {
                //先将指针移动到该条记录开始位置
                raf.seek(i*100);
                //读取用户名
                byte[] data=new byte[32];
                raf.read(data);
                String name= new String(data,"UTF-8").trim();
                //知否为该用户
                if (name.equals(username)){
                    //读取密码
                    raf.read(data);
                    String pwd=new String(data,"UTF-8").trim();
                    if (pwd.equals(password)){
                    //登入成功
                        check=true;
                        break;
                    }
                }
            }
            if (check){
                response.setEntity(new File("./pjmaven16/webapps/myweb/login_success.html"));
            }else {
                response.setEntity(new File("./pjmaven16/webapps/myweb/login_fail.html"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登入成功");
    }
}
