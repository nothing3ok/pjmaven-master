package Reflect;

import java.util.Scanner;

public class ReflectDemo2 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /**
         * 1����ʵ��������������
         * 2 ͨ����Ķ���ʵ����
         */
        try {
            System.out.println("����������Ҫʵ����������");
            Scanner sca=new Scanner(System.in);
            String className=sca.nextLine();
            Class cls=Class.forName(className);
           Object obj= cls.newInstance();
            /**
             * class �ṩ��ʵ��������ķ�����
             * object new instance����
             * �������Ҫ��class��ʾ����������޲ι��췽����
             * ������׳��쳣
             * ����ǰ��û���޲ι��췽����������Ҫ��ͨ�������
             * ��ȡ�䶨���ĳ�ֹ��췽����Ȼ��ͨ������ŷ�����
             * ������ʵ����
             */
            System.out.println(obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
