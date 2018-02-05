package algorithms.chapter2;

/**
 * <dl>
 * <dt>MinPQ</dt>
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
public class MinPQ<Key extends Comparable<Key>> {

    private int maxSize;
    private int N = 0;
    Key[] pq;

    public MinPQ(){
        this(100);
    }

    public MinPQ(int max) {
        this.maxSize = max;
        pq = (Key[])new Comparable[maxSize + 1];
    }

    public void insert(Key key) {
        if (N == maxSize) {
            throw new RuntimeException("MinPQ最大容量为" + maxSize);
        }
        pq[++N] = key;
        swim(N);
    }

    private void swim(int i) {
        int temp = i;
        while(temp > 1) {
            int parent = parent(temp);
            if (pq[parent].compareTo(pq[temp]) > 0) {
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
        int min = index;
        if (left(index) <= N && pq[left(index)].compareTo(pq[min]) < 0) {
            min = left(index);
        }
        if (right(index) <= N && pq[right(index)].compareTo(pq[min]) < 0) {
            min = right(index);
        }
        if (min != index) {
            swap(index, min);
            sink(min);
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

    public Key min() {
        if (N == 0) {
            return null;
        }
        return pq[1];
    }

    public Key delMin() {
        if (N == 0) {
            throw new RuntimeException("MinPQ为空");
        }
        Key max = min();
        swap(1,N--);
        sink(1);
        return max;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        MinPQ<Integer> maxPQ = new MinPQ<Integer>(100);
        maxPQ.insert(1);
        System.out.println(maxPQ.min());
        maxPQ.insert(10);
        System.out.println(maxPQ.min());
        maxPQ.insert(2);
        System.out.println(maxPQ.min());
        maxPQ.insert(11);
        System.out.println(maxPQ.min());
        System.out.println(maxPQ.size());
        System.out.println("------------------");
        System.out.println( maxPQ.delMin());
        System.out.println( maxPQ.delMin());
        System.out.println( maxPQ.delMin());
        System.out.println( maxPQ.delMin());
        System.out.println( maxPQ.delMin());
        System.out.println( maxPQ.delMin());


    }

}
