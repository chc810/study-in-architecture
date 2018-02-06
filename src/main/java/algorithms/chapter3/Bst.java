package algorithms.chapter3;

/**
 * <dl>
 * <dt>Bst</dt>
 * <dd>Description:二叉查找树</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/2/6</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Bst<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private int N;
        private Node left;
        private Node right;
        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public void put(Key key, Value value) {
      /*  if (root == null) {
            root = new Node(key,value,1);
            return;
        }
        put(root, key, value);*/
      root = put(root, key, value);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    public Key floor(Key key) {
        Node node = floor(key, root);

        if (node == null) {
            return null;
        }
        return node.key;
    }

   /* public Key select(int k) {

    }

    private Node select(Node node, int k) {
        if (node == null) {

        }
    }*/

    private Node floor(Key key, Node node) {
        if (node == null) {
            return null;
        }
        int r = key.compareTo(node.key);
        if (r < 0) {
            return floor(key,node.left);
        } else if (r > 0) {
            Node temp = floor(key, node.right);
            //注意下面的处理方式
            if (temp == null) {
                return node;
            }
            return temp;
        } else {
            return node;
        }
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int r = key.compareTo(node.key);
        if (r == 0) {
            return node.value;
        } else if (r < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.print(node.key + " ");
        print(node.right);
    }

    /**
     * 这种写法可以改进
     * @param node
     * @param key
     * @param value
     */
   /* private void put(Node node, Key key, Value value) {
        if (node.key.compareTo(key) == 0) {
            //相等
            node.value = value;
            return;
        }
        if (key.compareTo(node.key) < 0 ) {
            //左子树
            if (node.left == null) {
                node.left = new Node(key,value,1);
            } else {
                put(node.left, key, value);
            }
        } else {
            //右子树
            if (node.right == null) {
                node.right = new Node(key,value,1);
            } else {
                put(node.right, key, value);
            }
        }
        node.N = size(node.left) + size(node.right) + 1;
    }*/

   private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key,value,1);
        }
       if (node.key.compareTo(key) == 0) {
           //相等
           node.value = value;
           return node;
       }
       if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
       } else {
            node.right = put(node.right, key, value);
       }
       node.N = size(node.left) + size(node.right) + 1;
       return node;
   }



    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    public static void main(String[] args) {
        Bst<Integer, Integer> bst = new Bst<>();
        bst.put(8,8);
        bst.put(3,3);
        bst.put(19,19);
        bst.put(3,3);
        bst.put(7,7);
        bst.put(20,20);
        bst.print();
        System.out.println("-------------------");
        System.out.println(bst.get(19));
        System.out.println(bst.get(2));
        System.out.println("max=" + bst.max());
        System.out.println("min=" + bst.min());
        System.out.println("floor,9==" + bst.floor(9));
        System.out.println("floor,19==" + bst.floor(19));
        System.out.println("floor,2==" + bst.floor(2));
        System.out.println("floor,100==" + bst.floor(100));
    }


}
