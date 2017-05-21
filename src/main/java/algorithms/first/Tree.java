package algorithms.first;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
public class Tree {

    @Test
    public void main() {
       /* List<String> inOrder = Arrays.asList(new String[]{"D","B","G","E","A","F","C",});
        List<String> postOrder = Arrays.asList(new String[]{"D","G","E","B","F","C","A",});*/

        /*List<String> inOrder = Arrays.asList(new String[]{"E","D","C","B","A"});
        List<String> postOrder = Arrays.asList(new String[]{"E","D","C","B","A"});*/

        List<String> inOrder = Arrays.asList(new String[]{"A","B","C","D","E"});
        List<String> postOrder = Arrays.asList(new String[]{"E","D","C","B","A"});
        TreeNode treeNode = buildTreeWithInorderPostorder(inOrder, 0, inOrder.size() - 1, postOrder, postOrder.size() - 1);
        printTreeFront(treeNode);
    }


    /**
     *
     * 根据中序和后序遍历求树
     * @param inOrder  中序遍历
     * @param begin
     * @param end
     * @param postOrder  后续遍历
     * @param pos
     * @return
     */
    public TreeNode buildTreeWithInorderPostorder(List<String> inOrder, int begin, int end, List<String> postOrder, int pos) {
        TreeNode treeNode = new TreeNode();
        String nodeData = postOrder.get(pos);
        treeNode.setData(nodeData);
        if (end == begin) {
            treeNode.setLeft(null);
            treeNode.setRight(null);
            return treeNode;
        }

        int posIndex = inOrder.indexOf(nodeData);
        TreeNode right = null;
        TreeNode left = null;
        if (posIndex != end) {  //临界值判断
            right = buildTreeWithInorderPostorder(inOrder,posIndex + 1, end,postOrder,pos - 1);
        }
        if (pos-end+posIndex-1 >= 0) {  //临界值判断
            left = buildTreeWithInorderPostorder(inOrder, begin, posIndex - 1, postOrder, pos-end+posIndex-1);
        }
        treeNode.setLeft(left);
        treeNode.setRight(right);
        return treeNode;
    }

    public void printTreeFront(TreeNode tree) {
        if (tree == null) {
            return;
        }
        System.out.println(tree.getData());
        printTreeFront(tree.getLeft());
        printTreeFront(tree.getRight());
    }

}
