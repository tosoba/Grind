package leetcode.tree;

public class IsBalanced {
  private boolean balanced = true;

  public boolean isBalanced(TreeNode root) {
    if (root == null || (root.left == null && root.right == null)) return true;
    height(root);
    return balanced;
  }

  private int height(TreeNode root) {
    if (!balanced) return -1;
    if (root == null) return 0;
    int hl = height(root.left);
    int hr = height(root.right);
    if (Math.abs(hl - hr) > 1) balanced = false;
    return 1 + Math.max(hl, hr);
  }

  public static void main(String[] args) {}

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
