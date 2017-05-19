package algorithms.first;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <dl>
 * <dt>StepIssue</dt>
 * <dd>Description:阶梯问题，每次可以跨1步或2步，到N台阶，有多少种走法</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/5/18</dd>
 * </dl>
 *
 * @author cuihc
 */
public class StepIssue {



    public static int nStep = 4;

    @Test
    public void main() {
        List<Integer> steps = new ArrayList<Integer>();
        solve(steps,0);
    }

    public void solve(List<Integer> steps, int n) {
        if (n == nStep) {
            System.out.println(steps);
            return;
        }
        steps.add(steps.size(),1);
        System.out.println("step:1");
        solve(steps, n+1);
        steps.remove(steps.size() - 1);   //回溯
        if (n + 2<= nStep) {
            steps.add(steps.size(),2);
            System.out.println("step:2");
            solve(steps, n+2);
            steps.remove(steps.size() - 1);  //回溯
        }
    }
}
