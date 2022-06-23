package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidateBinaryTree {
  private boolean isValidBST(TreeNode root, int prevRootVal, boolean left) {
    if (root == null) return true;
    if (root.left == null && root.right == null) {
      return ((left && prevRootVal > root.val) || (!left && prevRootVal < root.val));
    }
    if (root.left != null && root.right != null) {
      return root.val > root.left.val
          && root.val < root.right.val
          && ((left && prevRootVal > root.left.val && prevRootVal > root.right.val)
              || (!left && prevRootVal < root.left.val && prevRootVal < root.right.val))
          && isValidBST(root.left, root.val, true)
          && isValidBST(root.right, root.val, false);
    }
    if (root.left != null) {
      return root.val > root.left.val
          && ((left && prevRootVal > root.left.val) || (!left && prevRootVal < root.left.val))
          && isValidBST(root.left, root.val, true);
    } else {
      return root.val < root.right.val
          && ((left && prevRootVal > root.right.val) || (!left && prevRootVal < root.right.val))
          && isValidBST(root.right, root.val, false);
    }
  }

  public boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    if (root.left == null && root.right == null) return true;
    if (root.left != null && root.right != null) {
      return root.val > root.left.val
          && root.val < root.right.val
          && isValidBST(root.left, root.val, true)
          && isValidBST(root.right, root.val, false);
    }
    if (root.left != null) {
      return root.val > root.left.val && isValidBST(root.left, root.val, true);
    } else {
      return root.val < root.right.val && isValidBST(root.right, root.val, false);
    }
  }

  public static void main(String[] args) {
    var test = new ValidateBinaryTree();
    var root =
        test.listToRoot(
            Stream.of(120, 70, 140, 50, 100, 130, 160, 20, 55, 75, 110, 119, 135, 150, 200)
                .collect(Collectors.toList()));
    System.out.println(test.isValidBST(root));
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

  private TreeNode listToRoot(List<Integer> vals) {
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
