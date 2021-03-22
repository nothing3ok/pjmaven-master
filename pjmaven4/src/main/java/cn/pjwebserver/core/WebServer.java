package cn.pjwebserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private ServerSocket server;
    public WebServer() {


        try {
            server = new ServerSocket(8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start(){
        try {
            System.out.println("等待链接.....");
            Socket socket=server.accept();
            System.out.println("链接了......");
            ClientHandler handler=new ClientHandler(socket);
            Thread t=new Thread(handler);
            t.start();
        }catch  (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebServer server=new WebServer();
        server.start();
    }
}
