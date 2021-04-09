package Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo4 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class cla=Class.forName("Reflect.person");
        Object o=cla.newInstance();

        Method m=o.getClass().getMethod("sayHei", String.class, int.class);
        m.invoke(o,"zhangshan",12);
    }
}
