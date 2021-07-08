import java.util.Random;

public class shudu {
    /** �洢���ֵ����� */
    private static int[][] n = new int[9][9];
    /** ����������ֵ�Դ���飬������ִӸ������в��� */
    private static int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public static int[][] generateShuDu(){
        // ��������
        for (int i = 0; i < 9; i++) {
            // �����������ִ���
            int time = 0;
            // �������
            for (int j = 0; j < 9; j++) {
                // ��������
                n[i][j] = generateNum(time);
                // �������ֵΪ0�������ס���˻ش���
                // �˻ش����ԭ���ǣ�������ǵ�һ�У����ȵ��˵�ǰһ�У������˵�ǰһ�е����һ��
                if (n[i][j] == 0) {
                    // ���ǵ�һ�У�����һ��
                    if (j > 0) {
                        j -= 2;
                        continue;
                    } else {// �ǵ�һ�У����˵���һ�е����һ��
                        i--;
                        j = 8;
                        continue;
                    }
                }
                // ���ɹ�
                if (isCorret(i, j)) {
                    // ��ʼ��time��Ϊ��һ�������׼��
                    time = 0;
                } else { // �������
                    // ��������1
                    time++;
                    // ������䵱ǰ��
                    j--;
                }
            }
        }
        return n;
    }

    /**
     * �Ƿ������С��к�3X3�����ظ���Ҫ��
     *
     * @param row
     *            �к�
     * @param col
     *            �к�
     * @return true�������Ҫ��
     */
    private static boolean isCorret(int row, int col) {
        return (checkRow(row) & checkLine(col) & checkNine(row, col));
    }

    /**
     * ������Ƿ����Ҫ��
     *
     * @param row
     *            �����к�
     * @return true�������Ҫ��
     */
    private static boolean checkRow(int row) {
        for (int j = 0; j < 8; j++) {
            if (n[row][j] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (n[row][j] == n[row][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ������Ƿ����Ҫ��
     *
     * @param col
     *            �����к�
     * @return true�������Ҫ��
     */
    private static boolean checkLine(int col) {
        for (int j = 0; j < 8; j++) {
            if (n[j][col] == 0) {
                continue;
            }
            for (int k = j + 1; k < 9; k++) {
                if (n[j][col] == n[k][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ���3X3�����Ƿ����Ҫ��
     *
     * @param row
     *            �����к�
     * @param col
     *            �����к�
     * @return true�������Ҫ��
     */
    private static boolean checkNine(int row, int col) {
        // ������Ͻǵ�����
        int j = row / 3 * 3;
        int k = col / 3 * 3;
        // ѭ���Ƚ�
        for (int i = 0; i < 8; i++) {
            if (n[j + i / 3][k + i % 3] == 0) {
                continue;
            }
            for (int m = i + 1; m < 9; m++) {
                if (n[j + i / 3][k + i % 3] == n[j + m / 3][k + m % 3]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ����1-9֮���������� �������ɵ�������ַ���������8-time�±��λ�ã�����time�����ӣ��Ѿ����Թ������ֽ�������ȡ��
     * ˵��������һ�δ��Ǵ�����������������ڶ���ʱ��ǰ�˸�������������������ƣ� �����ȱ�֤�����Ҳ�������ظ�ȡ�Ѿ�������Ҫ������֣���߳����Ч��
     * ��������Ǳ��㷨�ĺ���
     *
     * @param time
     *            ���Ĵ�����0�����һ�����
     * @return
     */
    private static Random r=new Random();
    private static int generateNum(int time) {
        // ��һ�γ���ʱ����ʼ���������Դ����
        if (time == 0) {
            for (int i = 0; i < 9; i++) {
                num[i] = i + 1;
            }
        }
        // ��10����䣬������λ���Ѿ���ס���򷵻�0�������������˻�
        if (time == 9) {
            return 0;
        }
        // ���ǵ�һ�����
        // ����������֣���������������±꣬ȡ����num�и��±��Ӧ������Ϊ�������
	//	int ranNum = (int) (Math.random() * (9 - time));//j2se
        int ranNum=r.nextInt(9 - time);//j2me
        // �����ַ��������鵹����time��λ�ã�
        int temp = num[8 - time];
        num[8 - time] = num[ranNum];
        num[ranNum] = temp;
        // ��������
        return num[8 - time];
    }

    public static void main(String[] args) {
        int[][] shuDu=generateShuDu();
        // ������
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(shuDu[i][j]+" ");
            }
            System.out.println();
        }
    }

}
