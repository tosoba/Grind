package leetcode.tree;

public class BinaryTreeMaxDepth {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int maxDepth(TreeNode root, int currentDepth) {
        if (root == null) return currentDepth;
        return Math.max(root.left != null ? maxDepth(root.left, currentDepth + 1) : currentDepth,
                root.right != null ? maxDepth(root.right, currentDepth + 1) : currentDepth);

    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return Math.max(maxDepth(root.left, 1), maxDepth(root.right, 1)) + 1;
    }

    public static void main(String[] args) {

    }
}
