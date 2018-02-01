package algorithms.chapter2;

/**
 * <dl>
 * <dt>MaxPQ</dt>
 * <dd>Description:优先队列，插入删除的复杂度都为O(lgN)
 * 使用数组方式
 *
 * </dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/1</dd>
 * </dl>
 *
 * @author cuihc
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private int maxSize;
    private int N = 0;
    Key[] pq;

    public MaxPQ(){
        this(100);
    }

    public MaxPQ(int max) {
        this.maxSize = max;
        pq = (Key[])new Comparable[maxSize + 1];
    }

    public void insert(Key key) {
        if (N == maxSize) {
            throw new RuntimeException("MaxPQ最大容量为" + maxSize);
        }
        pq[++N] = key;
        swim(N);
    }

    private void swim(int i) {
        int temp = i;
        while(temp > 1) {
            int parent = parent(temp);
            if (pq[parent].compareTo(pq[temp]) < 0) {
                swap(parent, temp);
                temp = parent;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void sink(int index) {
        if (index >= N) {
            return;
        }
        int max = index;
        if (left(index) <= N && pq[left(index)].compareTo(pq[max]) > 0) {
            max = left(index);
        }
        if (right(index) <= N && pq[right(index)].compareTo(pq[max]) > 0) {
            max = right(index);
        }
        if (max != index) {
            swap(index, max);
            sink(max);
        }
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

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        if (N == 0) {
            throw new RuntimeException("MaxPQ为空");
        }
        Key max = max();
        swap(1,N--);
        sink(1);
        return max;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(100);
        maxPQ.insert(1);
        System.out.println(maxPQ.max());
        maxPQ.insert(10);
        System.out.println(maxPQ.max());
        maxPQ.insert(2);
        System.out.println(maxPQ.max());
        maxPQ.insert(11);
        System.out.println(maxPQ.max());
        System.out.println(maxPQ.size());
        System.out.println("------------------");
        System.out.println( maxPQ.delMax());
        System.out.println( maxPQ.delMax());
        System.out.println( maxPQ.delMax());
        System.out.println( maxPQ.delMax());
        System.out.println( maxPQ.delMax());
        System.out.println( maxPQ.delMax());

    }

}
