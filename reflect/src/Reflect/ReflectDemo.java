package Reflect;

import java.lang.reflect.Method;

/**
 * java 反射机制
 * java 反射之际是一种动态机制，他允许我们加载一个类实例化一个类，
 * 调用方法操作属性编码期间确定转为在运行期间确定
 * 这样做可以大大提高代码的灵活度，但是反射有更大的资源开销，所以不能通过依赖反射
 */
public class ReflectDemo {
    /**
     * class 类
     * class类的每一个实例用于表示jvm已经加载的一个类
     * 并且在jvm 内部每个被加载的类都有且只有一个class
     * 的实例于对应，
     * 通过类对象我们可以
     * 获取其表示的类的名字，构造方法，方法，属性，并可以快速实例化
     *
     * 获取一个类的类对象有一下方式“
     * 1 直接通过类的静态属性class得到
     * 比如 获取string的类对象，我们可以
     * class cls=string.class
     *
     * 2 通过class 的静态方法 forname 加载：
     * class cls=class.fromname（”java.lang.string“）
     *
     * 3 通过加载类器 classloader
     */

    //加载string
    Class cls;

    {
        try {
            //加载string
            cls = Class.forName("java.lang.String");
            //获取类名
            String name=cls.getName();
            System.out.println(name);
            //获取所有方法
            Method[] methods=cls.getMethods();//获取所有方法 从父类继承下来的方法也包括在里面
            Method[] methods1=cls.getDeclaredMethods();//获取自己类里面的方法

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
