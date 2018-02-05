package algorithms.chapter2;

/**
 * <dl>
 * <dt>IndexMinPQ</dt>
 * <dd>Description:索引优先队列</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/5</dd>
 * </dl>
 *
 * @author cuihc
 */
public class IndexMaxPQ<Key extends Comparable> {

    private int N;
    private int[] pq;   //由1开始
    private int[] qp;   //如果某索引不在队列中中返回-1
    private Key[] keys;

    public IndexMaxPQ(int maxN) {
        keys = (Key[])new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i= 0;i<=maxN;i++) {
            qp[i] = -1;
        }
    }

    public IndexMaxPQ(){}

    void insert(int k, Key key) {
        if (k > qp.length - 1) {
            throw new RuntimeException("索引k=" + k + "超过了队列最大值" + (qp.length - 1));
        }
        if (keys[k] == null) {
            keys[k] = key;
            qp[++N] = k;
            pq[k] = N;
            swim(N);
        } else {
            change(k, key);
        }
    }

    public Key max() {
        return keys[pq[1]];
    }

    public int maxIndex() {
        return pq[1];
    }

    public boolean contains(int k) {
        if (qp[k] == -1) {
            return false;
        }
        return true;
    }

    private void change(int k, Key key) {
    }

    private void swim(int i) {
        int temp = i;
        while(temp > 1) {
            int parent = parent(temp);
            if (keys[pq[parent]].compareTo(keys[pq[temp]]) < 0) {
                swap(parent, temp);
                temp = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        int tempValue = qp[pq[i]];
        qp[pq[i]] = qp[pq[j]];
        qp[pq[j]] = tempValue;
    }

    private int parent(int index) {
        return index / 2;
    }
    private int left(int index) {
        return 2 * index;
    }
    private int right(int index) {
        return left(index) + 1;
    }


    public static void main(String[] args) {
        IndexMaxPQ<Integer> indexMinPQ = new IndexMaxPQ<>(10);
        indexMinPQ.insert(1,1);
        System.out.println(indexMinPQ.max());
        indexMinPQ.insert(2,10);
        System.out.println(indexMinPQ.max());
        indexMinPQ.insert(3,2);
        System.out.println(indexMinPQ.max());
        indexMinPQ.insert(4,11);
        System.out.println(indexMinPQ.max());
        System.out.println(indexMinPQ.contains(5));
    }

}
