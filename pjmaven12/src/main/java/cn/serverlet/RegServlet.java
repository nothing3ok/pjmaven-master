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
 * 注册业务
 */
public class RegServlet {
    public void Servlet(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("RegServlet:开始注册用户业务");

        /**
         * 1:通过request 获取用户在驻车页面上输入的注册信息
         * 2 将该用户信息接入到文件user。dat中保存起来
         * 3 响应客户端注册成功页面
         */
        //1
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int age= Integer.parseInt(request.getParameter("age"));
        String sex=request.getParameter("sex");
        System.out.println(username+","+password+","+age+","+sex);

        /**
         * 2
         * 将用户信息写道文件里面去
         */
        try(RandomAccessFile raf=new RandomAccessFile("user.dat","rw")) {
          //将指针移动到文件末尾
            raf.seek(raf.length());
            //写同户名
            byte[] data=username.getBytes("UTF-8");
            data= Arrays.copyOf(data,32);
            raf.write(data);//写了32字节
            //写密码
            data=password.getBytes("UTF-8");
            data=Arrays.copyOf(data,32);
            raf.write(data);
            //写年龄
            data=sex.getBytes("UTF-8");
            data=Arrays.copyOf(data,32);
            raf.write(data);
            //写年龄
            raf.writeInt(age);
            //3 response ,响应注册成功
            response.setEntity(new File("./pjmaven12/webapps/myweb/reg_success.html"));

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("RegServlet:注册用户业务完毕");
    }
}
