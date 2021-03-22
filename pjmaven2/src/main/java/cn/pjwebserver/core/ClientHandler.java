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
            StringBuilder builder=new StringBuilder();
            int c1=-1,c2=-1;
            while ((c2=in.read())!=-1){
                if (c1==13&&c2==10){
                    break;}
            }
            String line =builder.toString().trim();
            System.out.println(line);
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
