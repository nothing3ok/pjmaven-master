package Reflect;

/**
 * 使用这个类测试反射机制
 */
public class person {
    public void sayHello() {
        System.out.println("hello");
    }
    public void sayHei() {
        System.out.println("hi");
    }
    public void sayHello(String name) {
        System.out.println("hi,我是"+name);
    }
    public void sayHei(String name,int age) {
        System.out.println("hi我是"+name+"今年"+age);
    }

    /**
     * 私有方法
     */
    private void say(){
        System.out.println( "来了老弟");
    }

}
