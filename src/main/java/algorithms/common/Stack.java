package algorithms.common;

/**
 * <dl>
 * <dt>Stack</dt>
 * <dd>Description:栈的实现</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/1/31</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Stack<T> {

    private static final int DEFAULT_SIZE = 10;

    //注意，此处智能使用强转
    private T[] arr = (T[])new Object[DEFAULT_SIZE];

    private int size = 0;

    /**
     * 弹出
     * @return
     */
    public T pop() {
        if (size < arr.length / 2) {
            resize(arr.length / 2);
        }
        if (size == 0) {
            return null;
        }
        return arr[--size];

    }

    /**
     * 压栈
     */
    public void push(T t) {
        if (arr.length == size) {
            resize(2 * size);
        }
        arr[size++] = t;
    }

    public int size() {
        return size;
    }

    private void resize(int msize) {
        T[] tempArr = (T[])new Object[msize];
        for (int i =0;i<size;i++) {
            tempArr[i] = arr[i];
        }
        arr = tempArr;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        for (Integer i=0;i<100;i++) {
            stack.push(i);
        }
       while (stack.size() != 0) {
            System.out.print(stack.pop() + " ");
       }
    }

}
