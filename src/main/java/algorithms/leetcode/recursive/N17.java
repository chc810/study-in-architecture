package algorithms.leetcode.recursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>N17</dt>
 * <dd>Description:17.Letter Combinations of a Phone Number</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/1</dd>
 * </dl>
*        Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
         A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

 下面有先序和后序的解法，多叉树怎么定义中序？
 * @author cuihc
 */
public class N17 {

    class Solution {
        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return new ArrayList<>();
            }
            initChar();
            return solution(digits.toCharArray(),0);
        }

        private Map<Character,char[]> charMap = new HashMap<Character,char[]>();

        /**
         * 解决digistArr字符串，从index开始的字符可以组合成的结果集合
         * 最后才返回最终结果，类似后续遍历
         * @param digistArr
         * @param index
         * @return
         */
        private List<String> solution(char[] digistArr, int index) {
            if (index >= digistArr.length) {
                List<String> list = new ArrayList<>();
                list.add("");
                return list;
            }
            char theChar = digistArr[index];
            List<String> ret = new ArrayList<>();
            char[] chars = charMap.get(theChar);
            List<String> nextRet = solution(digistArr, index + 1);
            for (char cc : chars) {
                for (String s : nextRet) {
                    ret.add(cc + s);
                }
            }
            return ret;
        }

        private void initChar() {
            charMap.put('2', new char[]{'a','b','c'});
            charMap.put('3', new char[]{'d','e','f'});
            charMap.put('4', new char[]{'g','h','i'});
            charMap.put('5', new char[]{'j','k','l'});
            charMap.put('6', new char[]{'m','n','o'});
            charMap.put('7', new char[]{'p','q','r','s'});
            charMap.put('8', new char[]{'t','u','v'});
            charMap.put('9', new char[]{'w','x','y','z'});
        }
    }

    class Solution1 {
        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) {
                return new ArrayList<>();
            }
            initChar();
            solution(digits.toCharArray(), 0, "");
            return list;
        }

        private Map<Character,char[]> charMap = new HashMap<Character,char[]>();

        private List<String> list = new ArrayList<>();

        /**
         * 对于digistArr的字符数组，index表示：当前要计算index的选择，s代表：前index-1计算完后生成的字符串
         * 先访问前面的，到根就是一个解，类似先序遍历
         * @param digistArr
         * @param index
         * @param s
         */
        private void solution(char[] digistArr, int index, String s) {
            if (index == digistArr.length) {
                list.add(s);
                return;
            }
            char theChar = digistArr[index];
            char[] chars = charMap.get(theChar);
            for (char cc : chars) {
                solution(digistArr,index + 1, s + cc);
            }
        }

        private void initChar() {
            charMap.put('2', new char[]{'a','b','c'});
            charMap.put('3', new char[]{'d','e','f'});
            charMap.put('4', new char[]{'g','h','i'});
            charMap.put('5', new char[]{'j','k','l'});
            charMap.put('6', new char[]{'m','n','o'});
            charMap.put('7', new char[]{'p','q','r','s'});
            charMap.put('8', new char[]{'t','u','v'});
            charMap.put('9', new char[]{'w','x','y','z'});
        }
    }
}
