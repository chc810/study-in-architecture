package algorithms.chapter1;

import org.junit.Test;

public class E1_1_27 {

    private static double count1 = 0;
    private static double count2 = 0;
    private static double count3 = 0;
    private static double[][] ARRAY;
    private static double[][] ARRAY2;

    public static double binomial(int N, int k, double p) {
        System.out.println("进入N=" + N + ",k=" + k);
        count1++;
        if (N == 0 && k == 0) {
            return 1.0;
        }
        if (N < 0 || k < 0) {
            return 0.0;
        }
        return (1.0-p)*binomial(N-1, k, p) + p*binomial(N-1,k -1,p);
    }

    public static double binomial2(int N, int k, double p) {
        ARRAY = new double[N+1][k+1];
        for (int i = 0;i<=N;i++) {
            for (int j =0;j<=k;j++) {
                ARRAY[i][j] = -1;
            }
        }
        return bin(N,k,p);
    }

    public static double binomial3(int N, int k, double p) {
        ARRAY2 = new double[N+2][k+2];
        for (int i = 0;i<=N + 1;i++) {
            for (int j =0;j<=k+1;j++) {
                ARRAY2[i][j] = -1;
            }
        }
        return bin2(N,k,p);
    }

    public static double bin(int N, int k, double p) {
        System.out.println("进入N=" + N + ",k=" + k);
        count2++;
        if (N == 0 && k == 0) {
            return 1.0;
        }
        if (N < 0 || k < 0) {
            return 0.0;
        }
        if (ARRAY[N][k] == -1) {
            ARRAY[N][k] = (1.0-p)*bin(N-1, k, p) + p*bin(N-1,k -1,p);
        }
        return ARRAY[N][k];
    }

    public static double bin2(int N, int k, double p) {
        System.out.println("进入N=" + N + ",k=" + k);
        count3++;
        if (N == 0 && k == 0) {
            return 1.0;
        }
        if (N < 0 || k < 0) {
            return 0.0;
        }
        if (ARRAY2[N][k+1] == -1) {
            ARRAY2[N][k+1] = bin2(N-1,k,p);
        }
        if (ARRAY2[N][k] == -1) {
            ARRAY2[N][k] = bin2(N-1,k-1,p);
        }
//        System.out.println("计算N=" + N + ",k=" + k + "，结果为=" + ((1.0-p)*ARRAY2[N][k+1] + p*ARRAY2[N][k]));
        return (1.0-p)*ARRAY2[N][k+1] + p*ARRAY2[N][k];

         /*   double a = bin2(N-1,k,p);
            double b = bin2(N-1,k-1,p);
        System.out.println("计算N=" + N + ",k=" + k + "，结果为=" + ((1.0-p)*a + p*b));
        return (1.0-p)*a + p*b;*/
    }



    @Test
    public void main() {
        System.out.println(binomial(5, 2, 0.25) + ",count1=" + count1);
        System.out.println(binomial2(5, 2, 0.25) + ",count2=" + count2);
        System.out.println(binomial3(5, 2, 0.25) + ",count3=" + count3);
    }


}
