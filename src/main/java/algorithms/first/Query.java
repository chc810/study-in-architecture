package algorithms.first;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <dl>
 * <dt>Query</dt>
 * <dd>Description:查找算法</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/5/17</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Query {

    @Test
    public void main() {

        List<Integer> list = Arrays.asList(new Integer[]{1,3,5,23,54,78,322,2345});
        System.out.println(query(list,0,list.size()-1,1));
    }

    /**
     * 递归的二分查找实现
     * @param list
     * @param start
     * @param end
     * @param key
     * @return
     */
    public int query(List<Integer> list, int start, int end, Integer key) {
        if(start > end) {
            return -1;
        }
        int tempIndex = (end + start) / 2;
        int temp = list.get(tempIndex);
        if (key == temp) {
            return tempIndex;
        } else if (key > temp) {
            System.out.println("进入大：start=" + (tempIndex+1) + ",end=" + end);
            return query(list,tempIndex + 1, end, key);
        } else {
            System.out.println("进入小：start=" + start + ",end=" + (tempIndex-1));
            return query(list,start, tempIndex-1, key);
        }
    }

    //整数排序 组成最大数 如 5,7,10,1 组成 75110最大
    public int sort(List<Integer> list) {

        return 0;
    }
}
