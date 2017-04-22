package algorithms.first;

import org.junit.Test;

/**
 * 最长公共子序列 LCS
 * Created by Administrator on 2017/4/22 0022.
 */
public class LCS {

    @Test
    public void main() {
//        Integer[][] is = new Integer[3][4];
//        System.out.println(is.length);
//        System.out.println(is[0].length);

        String[] x = {"a","c","d","f","g"};
        String[] y = {"a","d","f","c"};
        Integer[][] result = new Integer[y.length + 1][x.length + 1];
        lcs(x,y,result);
        for (int i=0;i<result.length;i++) {
            for(int j=0;j<result[0].length;j++) {
                System.out.print(result[i][j] + ",");
            }
            System.out.print("\n");
        }

    }

    public void lcs(String[] x, String[] y, Integer[][] result) {
        //初始化边缘数据
        for (int i=0;i<=x.length;i++) {
            result[0][i] = 0;
        }
        for (int i=0;i<=y.length;i++) {
            result[i][0] = 0;
        }

        for (int i=1;i<result.length;i++) {
            for(int j=1;j<result[0].length;j++) {
                if (x[j-1].equals(y[i-1])) {
                    result[i][j] = result[i-1][j-1] + 1;
                } else if (result[i-1][j] > result[i][j-1]){
                    result[i][j] = result[i-1][j];
                } else {
                    result[i][j] = result[i][j-1];
                }
            }
        }
    }
}
