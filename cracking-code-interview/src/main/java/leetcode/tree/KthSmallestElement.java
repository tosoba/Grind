package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KthSmallestElement {
  int visitedCount = 0;

  public int kthSmallestWayBetter(TreeNode root, int k) {
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

  private boolean leftMostReached = false;
  private Integer result = null;

  private int kthSmallest(TreeNode root, int k, int current) {
    if (root == null) return current;

    if (!leftMostReached && root.left == null) leftMostReached = true;
    current = kthSmallest(root.left, k, current);
    if (result != null) return current;
    if (leftMostReached) current++;
    if (current == k) {
      result = root.val;
      return current;
    }
    current = kthSmallest(root.right, k, current);
    return current;
  }

  public int kthSmallest(TreeNode root, int k) {
    if (root == null) throw new IllegalArgumentException();
    if (root.left == null) leftMostReached = true;
    int current = 0;
    current = kthSmallest(root.left, k, current);
    if (result != null) return result;
    ++current;
    if (current == k) {
      result = root.val;
      return result;
    }
    kthSmallest(root.right, k, current);
    if (result != null) return result;
    throw new IllegalArgumentException();
  }

  public static void main(String[] args) {
   new KthSmallestElement().kthSmallest(listToRoot(Stream.of(3,1,4,null,2).collect(Collectors.toList())), 1);
  }

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

  private static TreeNode listToRoot(List<Integer> vals) {
    var nodes = new ArrayList<TreeNode>(vals.size());
    for (int i = 0, valsSize = vals.size(); i < valsSize; i++) {
      Integer val = vals.get(i);
      if (val == null) {
        nodes.add(null);
        continue;
      }
      var node = new TreeNode(val);
      nodes.add(node);
      if (i == 0) continue;
      if ((i - 1) % 2 == 0) {
        nodes.get((i - 1) / 2).left = node;
      } else {
        nodes.get((i - 2) / 2).right = node;
      }
    }
    return nodes.get(0);
  }
}
