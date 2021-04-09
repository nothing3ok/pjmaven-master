package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ʹ�÷������˽�з���
 */
public class ReflecDemo5 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class cla=Class.forName("Reflect.person");

        Object o=cla.newInstance();

        /**
         * ������ⲿͨ���������˽�з������ƻ���װ��
         * ������Ǳ�Ҫ��������������󣬲�������������
         */
        Method m=cla.getDeclaredMethod("say");
        //�ڷ���˽�з���ǰ���÷��ʲ�����������ֱ�ӵ��ûᱨ��
        m.setAccessible(true);
        m.invoke(o);

        m.getModifiers();
    }
}
