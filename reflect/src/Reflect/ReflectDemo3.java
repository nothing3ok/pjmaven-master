package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectDemo3 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        person p=new person();
        p.sayHei();

        Scanner scanner=new Scanner(System.in);
        System.out.println("������Ҫʵ��������");
        String className=scanner.nextLine();
        //���÷���
        Class cls=Class.forName(className);
        Object o=cls.newInstance();
        System.out.println("��������õķ�����");
        String methodName=scanner.nextLine();
        //��ͨ��������ȡҪ���õķ���
        Method method=cls.getMethod(methodName);
        //����ָ������ķ���
        method.invoke(o);

    }
}
