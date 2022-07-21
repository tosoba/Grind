package leetcode.tree;

public class SubtreeOfAnother {
  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null || subRoot == null) return false;
    var foundSubRoot = findSubRootIn(root, subRoot);
    if (foundSubRoot == null) return false;
    if (equals(foundSubRoot, subRoot)) return true;
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  public TreeNode findSubRootIn(TreeNode root, TreeNode subRoot) {
    if (root == null || subRoot == null) return null;
    if (root.val == subRoot.val) return root;
    var left = findSubRootIn(root.left, subRoot);
    if (left != null) return left;
    return findSubRootIn(root.right, subRoot);
  }

  public boolean equals(TreeNode node1, TreeNode node2) {
    if (node1 == null && node2 == null) return true;
    if (node1 == null ^ node2 == null) return false;
    return node1.val == node2.val
        && equals(node1.left, node2.left)
        && equals(node1.right, node2.right);
  }

  public static void main(String[] args) {
    new SubtreeOfAnother().isSubtree(new TreeNode(1), new TreeNode(1));
  }

  private static final class TreeNode {
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
