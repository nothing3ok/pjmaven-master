package Reflect;

/**
 * ʹ���������Է������
 */
public class person {
    public void sayHello() {
        System.out.println("hello");
    }
    public void sayHei() {
        System.out.println("hi");
    }
    public void sayHello(String name) {
        System.out.println("hi,����"+name);
    }
    public void sayHei(String name,int age) {
        System.out.println("hi����"+name+"����"+age);
    }

    /**
     * ˽�з���
     */
    private void say(){
        System.out.println( "�����ϵ�");
    }

}
