package Reflect;

import java.util.Scanner;

public class ReflectDemo2 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /**
         * 1加载实例化的类的类对象
         * 2 通过类的对象实例化
         */
        try {
            System.out.println("请输入你想要实例化的类名");
            Scanner sca=new Scanner(System.in);
            String className=sca.nextLine();
            Class cls=Class.forName(className);
           Object obj= cls.newInstance();
            /**
             * class 提供了实例化对象的方法：
             * object new instance（）
             * 这个方法要求class表示的类必须由无参构造方法，
             * 否则会抛出异常
             * 若当前类没有无参构造方法，我们需要先通过类对象
             * 获取其定义的某种构造方法，然后通过构造放法对象
             * 来进行实例化
             */
            System.out.println(obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
