package leetcode.bst;

import java.util.ArrayList;

public class KthSmallestInBST {
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
    ArrayList<Integer> elements = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
      buildElementsInOrder(root);
      if (k > elements.size()) throw new IllegalArgumentException();
      return elements.get(k - 1);
    }

    private void buildElementsInOrder(TreeNode root) {
      if (root == null) return;
      if (root.left != null) buildElementsInOrder(root.left);
      elements.add(root.val);
      if (root.right != null) buildElementsInOrder(root.right);
    }
  }

  class Solution2 {
    int visitedCount = 0;

    public int kthSmallest(TreeNode root, int k) {
      return visitElementsInOrder(root, k);
    }

    private int visitElementsInOrder(TreeNode root, int k) {
      if (root == null) return -1;
      if (root.left != null) {
        var ret = visitElementsInOrder(root.left, k);
        if (ret != -1) return ret;
      }
      if (++visitedCount == k) return root.val;
      if (root.right != null) {
        var ret = visitElementsInOrder(root.right, k);
        if (ret != -1) return ret;
      }
      return -1;
    }
  }
}
