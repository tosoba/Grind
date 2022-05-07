package leetcode.bst;

import java.util.ArrayList;

public class IncreasingBST {
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

  class Solution {
    private final ArrayList<Integer> values = new ArrayList<>();

    public void buildList(TreeNode root) {
      if (root == null) return;
      if (root.left != null) buildList(root.left);
      values.add(root.val);
      if (root.right != null) buildList(root.right);
    }

    public TreeNode increasingBST(TreeNode root) {
      if (root == null) return null;
      buildList(root);
      var newRoot = new TreeNode(values.get(0));
      var current = newRoot;

      for (int i = 1, valuesSize = values.size(); i < valuesSize; i++) {
        var next = new TreeNode(values.get(i));
        current.right = next;
        current = next;
      }
      return newRoot;
    }
  }

  class Solution2 {
    private TreeNode newRoot;
    private TreeNode current;

    public void buildNewTree(TreeNode root) {
      if (root == null) return;
      if (root.left != null) buildNewTree(root.left);
      var next = new TreeNode(root.val);
      if (newRoot == null) newRoot = next;
      if (current != null) current.right = next;
      current = next;
      if (root.right != null) buildNewTree(root.right);
    }

    public TreeNode increasingBST(TreeNode root) {
      buildNewTree(root);
      return newRoot;
    }
  }
}
