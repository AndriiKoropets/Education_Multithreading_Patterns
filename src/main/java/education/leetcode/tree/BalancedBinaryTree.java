package education.leetcode.tree;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return check(root, root.left, root.right);
    }

    private boolean check(TreeNode root, TreeNode left, TreeNode right) {
        if (root != null && left == null && right == null) {
            return true;
        }

        if (root.val < right.val || root.val > left.val) {
            return false;
        }

        return check(left, left.left, left.right) && check(right, right.left, right.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3));

    }
}
