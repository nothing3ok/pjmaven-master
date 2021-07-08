import java.util.Random;

public class test02 {
    /**
     * 存储数字的数组
     */
    private static int[][] n = new int[9][9];
    /**
     * 生成随机数字的源数组，随机数字从该数组中产生
     */
    private static int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static int[][] generateShuDu() {


        return n;

    }

    public static void main(String[] args) {
        Random r=new Random();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               int c= n[i][j];
                System.out.println(c);
            }
        }

    }
}