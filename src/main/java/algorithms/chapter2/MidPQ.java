package algorithms.chapter2;

/**
 * <dl>
 * <dt>MidPQ</dt>
 * <dd>Description:中位数查找</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/5</dd>
 * </dl>
 *
 * @author cuihc
 */
public class MidPQ {
    private MaxPQ<Integer> maxPQ;
    private MinPQ<Integer> minPQ;

    public MidPQ(int maxSize){
        maxPQ = new MaxPQ<>(maxSize);
        minPQ = new MinPQ<>(maxSize);
    }

    public void insert(Integer key) {
        if (maxPQ.size() == minPQ.size()) {
            if (maxPQ.size() == 0 || (maxPQ.size() != 0 && key.compareTo(minPQ.min()) <= 0) ) {
                maxPQ.insert(key);
                return;
            }
            maxPQ.insert(minPQ.delMin());
            minPQ.insert(key);
        } else if (maxPQ.size() > minPQ.size()) {
            if (key.compareTo(maxPQ.max()) < 0 ) {
                minPQ.insert(maxPQ.delMax());
                maxPQ.insert(key);
            } else {
                minPQ.insert(key);
            }
        } else {
            if (key.compareTo(minPQ.min()) > 0) {
                maxPQ.insert(minPQ.delMin());
                minPQ.insert(key);
            } else {
                maxPQ.insert(key);
            }
        }


        return;
    }
    public Integer mid() {
        if (maxPQ.size() == minPQ.size()) {
            return (maxPQ.max() + minPQ.min()) / 2;
        } else if (maxPQ.size() > minPQ.size()) {
            return maxPQ.max();
        } else {
            return minPQ.min();
        }
    }

    public int delMid() {
        if (maxPQ.size() == minPQ.size()) {
            if (maxPQ.size() != 0) {
                return minPQ.delMin();
            }
            return -1;
        } else if (maxPQ.size() > minPQ.size()) {
            return maxPQ.delMax();
        } else {
            return minPQ.delMin();
        }
    }
}
