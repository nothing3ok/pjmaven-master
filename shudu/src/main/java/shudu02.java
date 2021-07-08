import java.util.Scanner;

public class shudu02 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextInt()){
            int[][]a=new int[9][9];
            boolean[][] cols = new boolean[9][9];
            boolean[][] rows = new boolean[9][9];
            boolean[][] blocks = new boolean[9][9];//�Ŵ󹬵ľŸ�����

            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    a[i][j]=sc.nextInt();
                    if(a[i][j]!=0){
                        int k = i/3*3+ j/3;//���־Ź���,�����������ȣ��Լ�Ҳ����������
                        int val=a[i][j]-1;
                        rows[i][val] = true;
                        cols[j][val] = true;
                        blocks[k][val] = true;
                    }
                }
            }//����װ�����
            DFS(a, cols, rows, blocks);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(a[i][j]+" ");
                }
                System.out.println(a[i][8]);
            }
        }
    }
    public static boolean DFS(int[][] a,boolean[][] cols,boolean[][] rows,boolean[][] blocks) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(a[i][j]==0){
                    int k=i/3*3+j/3;
                    for (int l = 0; l < 9; l++) {
                        if(!cols[j][l]&&!rows[i][l]&&!blocks[k][l]){//l���ڵ�����l+1û�������п��г���
                            rows[i][l] = cols[j][l] = blocks[k][l] = true;
                            a[i][j] = 1 + l;//�±��1
                            if(DFS(a, cols, rows, blocks)) return true;//�ݽ��򷵻�true
                            rows[i][l] = cols[j][l] = blocks[k][l] = false;//�ݽ�ʧ�������
                            a[i][j] = 0;
                        }
                    }
                    return false;//a[i][j]==0ʱ��l���ֶ��������ȥ
                }//the end of a[i][j]==0
            }
        }
        return true;//û��a[i][j]==0,�򷵻�true
    }


}
