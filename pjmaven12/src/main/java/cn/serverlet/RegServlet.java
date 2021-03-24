package cn.serverlet;

import cn.pjwebserver.http.HttpRequest;
import cn.pjwebserver.http.HttpResponse;

/**
 * 注册业务
 */
public class RegServlet {
    public void Servlet(HttpRequest request, HttpResponse response) {
        System.out.println("RegServlet:开始注册用户业务");

        /**
         * 1:通过request 获取用户在驻车页面上输入的注册信息
         * 2 将该用户信息接入到文件user。dat中保存起来
         *
         */



        System.out.println("RegServlet:注册用户业务完毕");
    }
}
