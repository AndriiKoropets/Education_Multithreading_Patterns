package education.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        treePath(root, result, "");
        return result;
    }

    private void treePath(TreeNode root, List<String> result, String current) {
        if (root != null && root.left == null && root.right == null) {
            result.add(current +  root.val);
            return;
        }
        if (root != null) {
            if (root.left != null) {
                treePath(root.left, result, current + root.val + "->");
            }
            if (root.right != null) {
                treePath(root.right, result, current + root.val + "->");
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3));
        BinaryTreePaths obj = new BinaryTreePaths();
        System.out.println(obj.binaryTreePaths(root));
    }
}
