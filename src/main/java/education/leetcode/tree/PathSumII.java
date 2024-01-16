package education.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathSumII {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> foundPath = new ArrayList<>(Collections.singleton(root.val));
        findPath(root, targetSum, foundPath);
        return result;
    }

    private void findPath(TreeNode node, int targetSum, List<Integer> foundPath) {
        if (targetSum - node.val == 0 && node.left == null && node.right == null) {
            result.add(new ArrayList<>(foundPath));
            return;
        }
        if (targetSum - node.val != 0 && node.left == null && node.right == null) {
            return;
        }

        if (node.left != null){
            foundPath.add(node.left.val);
            findPath(node.left, targetSum - node.val, foundPath);
            foundPath.remove(foundPath.size() - 1);
        }
        if (node.right != null) {
            foundPath.add(node.right.val);
            findPath(node.right, targetSum - node.val, foundPath);
            foundPath.remove(foundPath.size() - 1);
        }
    }

    public boolean isValidBST(TreeNode root) {
        return false;
    }



    public static void main(String[] args) {
        PathSumII ref = new PathSumII();
        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null), new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1))));
        System.out.println(ref.pathSum(root, 22));

    }


}
