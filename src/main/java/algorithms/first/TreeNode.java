package algorithms.first;

/**
 * Created by Administrator on 2017/5/21 0021.
 * 树节点
 */
public class TreeNode {

    private Object data;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Object data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }
}
