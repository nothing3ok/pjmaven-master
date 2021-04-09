package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用反射调用私有方法
 */
public class ReflecDemo5 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class cla=Class.forName("Reflect.person");

        Object o=cla.newInstance();

        /**
         * 在类的外部通过反射调用私有方法会破坏封装性
         * 如果不是必要操作或者设计需求，不建议这样做。
         */
        Method m=cla.getDeclaredMethod("say");
        //在访问私有方法前设置访问操作（不设置直接调用会报错）
        m.setAccessible(true);
        m.invoke(o);

        m.getModifiers();
    }
}
