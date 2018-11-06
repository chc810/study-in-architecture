package algorithms.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <dl>
 * <dt>N131</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/4</dd>
 * </dl>
 *
 * @author cuihc
 */
public class N131 {

    class Solution {
        public List<List<String>> partition(String s) {
            if (s == null || "".equals(s)) {
                return lists;
            }
            Stack<String> stack = new Stack<>();
            solution(s, 0, stack);
            return lists;
        }

        List<List<String>> lists = new ArrayList<>();

        private void solution(String s , int index, Stack<String> stack) {
            if (index == s.length()) {
                //找到一组
                List<String> list = new ArrayList<>();
                for (String str : stack) {
                    list.add(str);
                }
                lists.add(list);
                return;
            }

            for (int end = index; end<s.length(); end++) {
                if (isPalindrome(s, index, end)) {
                    String inStack = s.substring(index, end + 1);
                    stack.push(inStack);
                    solution(s,end + 1, stack);
                    stack.pop();
                }
            }
        }

        private boolean isPalindrome(String s, int start , int end) {
            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(new N131().new Solution().partition("aab")  );
    }
}
