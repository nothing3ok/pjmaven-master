package cn.pjwebserver.core;

import java.io.IOException;
import java.io.InputStream;
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
            //��ȡ������������
            InputStream in=socket.getInputStream();
            int d=-1;
            while ((d=in.read())!=-1){
                System.out.print((char)d);
            }
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
