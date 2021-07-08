import java.lang.reflect.Array;

public class test {
    public static void main(String[] args) {
        int [][] a =new int[][]{{123,321},{456,654}};
        for (int i=0; i<a.length;i++){
            for (int j = 0; j < a.length; j++) {
                int kk =a[i][j];
                System.out.println(kk);
            }
        }

//        int[][] arr = new int[][]{{4, 5, 6}, {2, 3, 9}};
//        int[][] arrtrans = new int[arr[0].length][arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[0].length; j++) {
//                arrtrans[j][i] = arr[i][j];
//            }
//        }
//        for (int i = 0; i < arrtrans.length; i++) {
//            for (int j = 0; j < arrtrans[0].length; j++) {
//                System.out.print(arrtrans[i][j]);
//            }
//            System.out.println();
//        }

//
//        int [][] arr=new int[][]{{4,5,6,8},{2,33},{1,6,9}};
//
//        System.out.println(arr.length);//输出行数
//        System.out.println(arr[0].length);//输出列数

    }
}
