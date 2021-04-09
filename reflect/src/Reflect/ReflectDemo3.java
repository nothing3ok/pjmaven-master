package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectDemo3 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        person p=new person();
        p.sayHei();

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要实例化的类");
        String className=scanner.nextLine();
        //利用反射
        Class cls=Class.forName(className);
        Object o=cls.newInstance();
        System.out.println("请输入调用的方法名");
        String methodName=scanner.nextLine();
        //先通过类对象获取要调用的方法
        Method method=cls.getMethod(methodName);
        //调用指定对象的方法
        method.invoke(o);

    }
}
