package algorithms.chapter3;

/**
 * <dl>
 * <dt>RedBlackBst</dt>
 * <dd>Description:红黑树实现</dd>
 *
 * 注意点：新插入的节点都是红节点，不管是左孩子还是右孩子，只有红节点才能保证树的高度不往下增加，跟2-3树对应
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/8</dd>
 * </dl>
 *
 * @author cuihc
 */
public class RedBlackBst<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private int N;
        private Node left;
        private Node right;
        private boolean color;
        public Node(Key key, Value value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    public boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }
}
