package Reflect;

import java.lang.reflect.Method;

/**
 * java �������
 * java ����֮����һ�ֶ�̬���ƣ����������Ǽ���һ����ʵ����һ���࣬
 * ���÷����������Ա����ڼ�ȷ��תΪ�������ڼ�ȷ��
 * ���������Դ����ߴ�������ȣ����Ƿ����и������Դ���������Բ���ͨ����������
 */
public class ReflectDemo {
    /**
     * class ��
     * class���ÿһ��ʵ�����ڱ�ʾjvm�Ѿ����ص�һ����
     * ������jvm �ڲ�ÿ�������ص��඼����ֻ��һ��class
     * ��ʵ���ڶ�Ӧ��
     * ͨ����������ǿ���
     * ��ȡ���ʾ��������֣����췽�������������ԣ������Կ���ʵ����
     *
     * ��ȡһ������������һ�·�ʽ��
     * 1 ֱ��ͨ����ľ�̬����class�õ�
     * ���� ��ȡstring����������ǿ���
     * class cls=string.class
     *
     * 2 ͨ��class �ľ�̬���� forname ���أ�
     * class cls=class.fromname����java.lang.string����
     *
     * 3 ͨ���������� classloader
     */

    //����string
    Class cls;

    {
        try {
            //����string
            cls = Class.forName("java.lang.String");
            //��ȡ����
            String name=cls.getName();
            System.out.println(name);
            //��ȡ���з���
            Method[] methods=cls.getMethods();//��ȡ���з��� �Ӹ���̳������ķ���Ҳ����������
            Method[] methods1=cls.getDeclaredMethods();//��ȡ�Լ�������ķ���

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
