package algorithms.leetcode.recursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <dl>
 * <dt>N93</dt>
 * <dd>Description: 93. Restore IP Addresses</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/2</dd>
 * </dl>
 *
 *     Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *     Example:
            Input: "25525511135"
            Output: ["255.255.11.135", "255.255.111.35"]
 *
 * @author cuihc
 */
public class N93 {

    class Solution {

        public List<String> restoreIpAddresses(String s) {
            solution(s, 0, new Stack<String>());
            return list;
        }

        private List<String> list = new ArrayList<>();

        private void solution(String s, int index, Stack<String> nums) {
            if (nums.size() == 4 && index == s.length()) {
                String str = "";
                for (String s1 : nums) {
                    str = str + s1 + ".";
                }
                list.add(str.substring(0, str.length() - 1));
                return;
            }
            if (nums.size() == 4 || index == s.length()) {
                return;
            }

            List<String> possiableNum = new ArrayList<>();
            char c = s.charAt(index);
            possiableNum.add(String.valueOf(c));
            if (index + 1 < s.length() && c != '0') {
                possiableNum.add(s.substring(index,index + 2));
            }
            if (index + 2 <s.length() && Integer.parseInt(s.substring(index,index + 3)) <= 255 && c != '0') {
                possiableNum.add(s.substring(index,index + 3));
            }
            for (int i =0; i<possiableNum.size();i++) {
                nums.push(possiableNum.get(i));
                solution(s, index + 1 + i, nums);
                nums.pop();
            }
        }
    }

    public static void main(String[] args) {
        List<String> list = new N93().new Solution().restoreIpAddresses("010010");
        System.out.println(list);
    }
}
