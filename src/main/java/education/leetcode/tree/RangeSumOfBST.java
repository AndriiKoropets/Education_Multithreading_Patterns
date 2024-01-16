package education.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RangeSumOfBST {

    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root != null) {
            countSum(root, low, high);
        }
        return sum;
    }

    private void countSum(TreeNode node, int low, int high) {
        if (node.val >= low && node.val <= high) {
            sum += node.val;
        }
        if (node.left != null) {
            countSum(node.left, low, high);
        }
        if (node.right != null) {
            countSum(node.right, low, high);
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }

        TreeNode left = searchBST(root.left, val);
        TreeNode right = searchBST(root.right, val);

        return left != null ? left : right;
    }

    int smallest = Integer.MAX_VALUE;
    int secondSmallest = -1;

    public int findSecondMinimumValue(TreeNode root) {

        if (root.val < smallest) {
            secondSmallest = smallest;
            smallest = root.val;
        }

        if (root.left != null) {
            findSecondMinimumValue(root.left);
        }
        if (root.right != null) {
            findSecondMinimumValue(root.right);
        }


        return secondSmallest == smallest ? -1 : secondSmallest;
    }


    private void findSecondMin() {
        int[] arr = new int[]{10,20,3};
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
//                secondMin = min;
                min = arr[i];
            }
            if (arr[i] < secondMin && arr[i] != min) {
                secondMin = arr[i];
            }
        }
        System.out.println(min);
        System.out.println(secondMin);
    }

    int binarySum = 0;

//    public int sumRootToLeaf(TreeNode root) {
//        countSum(root, new StringBuilder());
//
//        return binarySum;
//    }

    public int sumRootToLeaf(TreeNode root) {
        return pathSumRootToLeaf(root, 0);
    }

    private int pathSumRootToLeaf(TreeNode root, int parentNodeSum){
        if(root == null) return 0;

        parentNodeSum = 2 * parentNodeSum + root.val;
        if(root.left == null && root.right == null){
            return parentNodeSum;
        }

        return pathSumRootToLeaf(root.left, parentNodeSum) + pathSumRootToLeaf(root.right, parentNodeSum);
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        insertHelper(root, val);
        return root;
    }

    private void insertHelper(TreeNode node, int val) {
        if (node.left == null && node.right == null) {
            if (node.val > val) {
                node.left = new TreeNode(val);
            } else {
                node.right = new TreeNode(val);
            }
            return;
        } else if (node.val > val && node.left == null) {
            node.left = new TreeNode(val);
            return;
        } else if (node.val < val && node.right == null) {
            node.right = new TreeNode(val);
            return;
        }

        if (node.val > val && node.left != null) {
            insertHelper(node.left, val);
        }

        if (node.val < val && node.right != null) {
            insertHelper(node.right, val);
        }
    }

    public void flatten(TreeNode root) {
        TreeNode flattened = new TreeNode();
        flattenHelper(root, flattened);
        root = flattened;
    }

    private void flattenHelper(TreeNode node,  TreeNode cur) {
        if (node == null) return;
        else  {
            cur.right = new TreeNode(node.val);
            cur.left = null;
            cur = cur.right;
        }

        flattenHelper(node.left, cur);
        flattenHelper(node.right, cur);
    }

    private TreeNode increasingRoot;
    private TreeNode cur;

    public TreeNode increasingBST(TreeNode root) {
        increasingHelper(root);
        return increasingRoot;
    }

    private void increasingHelper(TreeNode node) {
        if (node == null) {
            return;
        }


        increasingHelper(node.left);
//        System.out.println(node.val);
//        TreeNode temp = new TreeNode(node.val);
//        if (increasingRoot == null) {
//            increasingRoot = temp;
//            cur = temp;
//        } else {
//            cur.right = temp;
//            cur = cur.right;
//        }

        increasingHelper(node.right);
        System.out.println(node.val);
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original.val == target.val) {
            return cloned;
        }

        TreeNode left = findTargetHelper(original.left, cloned.left, target);
        TreeNode right = findTargetHelper(original.right, cloned.right, target);

        return left == null ? right : left;
    }


    private final TreeNode findTargetHelper(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original.val == target.val) {
            return cloned;
        }

        TreeNode left = findTargetHelper(original.left, cloned.left, target);
        TreeNode right = findTargetHelper(original.right, cloned.right, target);

        return left == null ? right : left;
    }

    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (root.val == 1) {
                return true;
            } else {
                return false;
            }
        }

        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);

        if (root.val == 2) {
            return left || right;
        } else {
            return left && right;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root.val == key) {
            return root;
        }

        if (root.val > key) {
            deleteNode(root.left, key);
        } else {
            deleteNode(root.right, key);
        }
        return null;
    }

    public boolean isBalanced(TreeNode root) {
        return true;
    }


    private List<Integer> result;

    public List<Integer> postorder(Node root) {
        result = new ArrayList<>();
        postorderN(root);
        return result;
    }

    private void postorderN(Node node) {
        if (node == null) return;
        for (Node n : node.children) {
            postorderN(n);
            System.out.print(n.val);
        }
        result.add(node.val);
    }

    public boolean isUnivalTree(TreeNode root) {
        return univalueHelper(root, root.val);
    }

    private boolean univalueHelper(TreeNode node, int val) {
        if (node.val != val) {
            return false;
        }

        if (node.left != null) {
            if (!univalueHelper(node.left, val)) {
                return false;
            }
        }
        if (node.right != null) {
            if (!univalueHelper(node.right, val)) {
                return false;
            }
        }


        return true;
    }

    private List<Integer> tree1 = new ArrayList<>();
    private List<Integer> tree2 = new ArrayList<>();

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        leafSimilarHelper(root1, tree1);
        leafSimilarHelper(root2, tree2);

        return tree1.equals(tree2);
    }

    private void leafSimilarHelper(TreeNode node, List<Integer> leafs) {
        if (node.left == null && node.right == null) {
            leafs.add(node.val);
        }

        if (node.left != null) {
            leafSimilarHelper(node.left, leafs);
        }

        if (node.right != null) {
            leafSimilarHelper(node.right, leafs);
        }
    }

    StringBuilder sb = new StringBuilder();

    public String tree2str(TreeNode root) {
        tree2strHelper(root);
        return sb.toString();
    }

    private void tree2strHelper(TreeNode node) {
        if (node == null) {
            return;
        } else {
            sb.append(node.val);
        }

        if (node.left == null && node.right != null) {
            sb.append("()");
        }

        if (node.left != null) {
            sb.append("(");
            tree2strHelper(node.left);
            sb.append(")");
        }


        if (node.right != null) {
            sb.append("(");
            tree2strHelper(node.right);
            sb.append(")");
        }
    }

    TreeNode merged;
    TreeNode curNode;

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null) {
            curNode = new TreeNode(root1.val + root2.val);
        } else if (root1 != null) {
            curNode = new TreeNode(root1.val);
        } else if (root2 != null) {
            curNode = new TreeNode(root2.val);
        }

        if (root1 != null && root1.left != null || root2 != null && root2.left != null) {

        }

        return null;
    }

    private void mergeHelper(TreeNode root1, TreeNode root2, boolean left) {
        if (root1 != null && root2 != null) {
            curNode = new TreeNode(root1.val + root2.val);
        } else if (root1 != null) {
            curNode = new TreeNode(root1.val);
        } else if (root2 != null) {
            curNode = new TreeNode(root2.val);
        }

        TreeNode left1 = root1 != null && root1.left != null ? root1.left : null;
        TreeNode left2 = root2 != null && root2.left != null ? root2.left : null;
        TreeNode right1 = root1 != null && root1.right != null ? root1.right : null;
        TreeNode right2 = root2 != null && root2.right != null ? root2.right : null;

        mergeHelper(left1, left2, true);
        mergeHelper(right1, right2, false);
    }

    Set<Integer> subtraction = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        if (subtraction.contains(root.val)) {
            return true;
        }
        subtraction.add(k - root.val);

        return findTarget(root.left, k) || findTarget(root.right, k);
    }


    public static void main(String[] args) {
        RangeSumOfBST ref = new RangeSumOfBST();
//        TreeNode treeNode = new TreeNode(10, new TreeNode(5, new TreeNode(3), new TreeNode(7)), new TreeNode(15, null, new TreeNode(18)));
//        System.out.println(ref.rangeSumBST(treeNode, 7, 15));
//
//        TreeNode search = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7));
//        TreeNode result = ref.searchBST(search, 2);
//        System.out.println(result != null ? result.val : null);
//
//        TreeNode result1 = ref.searchBST(search, 5);
//        System.out.println(result1 != null ? result1.val : null);
//
//        TreeNode smallest1 = new TreeNode(2, new TreeNode(2), new TreeNode(5, new TreeNode(5), new TreeNode(7)));
//        System.out.println(ref.findSecondMinimumValue(smallest1));
//        TreeNode smallest2 = new TreeNode(2, new TreeNode(2), new TreeNode(2));
//        System.out.println(ref.findSecondMinimumValue(smallest2));

//        ref.findSecondMin();
//
//        TreeNode binary = new TreeNode(1, new TreeNode(0, new TreeNode(0), new TreeNode(1)), new TreeNode(1, new TreeNode(0), new TreeNode(1)));
//        System.out.println("binary sum");
//        System.out.println(ref.sumRootToLeaf(binary));


        TreeNode increasing11 = new TreeNode(5, new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)), new TreeNode(6, null, new TreeNode(8, new TreeNode(7), new TreeNode(9))));
        ref.increasingBST(increasing11);
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
