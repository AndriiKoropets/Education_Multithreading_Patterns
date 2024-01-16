package education.leetcode.tree;

public class MinimalDepth {

    private int MIN_DEPTH = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        countMinDepth(root, 1);
        return MIN_DEPTH;
    }

    private void countMinDepth(TreeNode node, int currentDepth) {
        if (node.left == null && node.right == null) {
            if (currentDepth < MIN_DEPTH) {
                MIN_DEPTH = currentDepth;
            }
            return;
        }

        if (node.left != null) {
            countMinDepth(node.left, currentDepth + 1);
        }
        if (node.right != null) {
            countMinDepth(node.right, currentDepth + 1);
        }
    }

    public static void main(String[] args) {
        MinimalDepth minimalDepth = new MinimalDepth();
//        TreeNode node = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode node = new TreeNode(2, new TreeNode(3, new TreeNode(4, new TreeNode(5, new TreeNode(6), null), null), null), null);
        System.out.println(minimalDepth.minDepth(node));
    }
}
