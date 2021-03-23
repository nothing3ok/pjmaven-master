package example;

public class text {
    public static void main(String[] args) {
        String a="fdsafewa?fjdklsa";
        String[] b=a.split("\\?");
        String c=b[0];
        String d=b[1];
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        for (String s : b) {
            String arr=b[0];
            System.out.println(arr);
            System.out.println(s);
        }
    }
}
