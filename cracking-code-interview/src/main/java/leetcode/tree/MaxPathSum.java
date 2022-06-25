package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxPathSum {
  private static final int MIN_MIN = -1_000_000;
  private int currentMaxSum = MIN_MIN;
  private int allSum = 0;

  private int maxSum = Integer.MIN_VALUE;

  // THIS works and maxPathSum does not because maxPathSum allows nodes to be passed more than
  // once...
  public int maxPathSum2(TreeNode root) {
    findMaxPathSum(root);
    return maxSum;
  }

  private int findMaxPathSum(TreeNode root) {
    if (root == null) return 0;

    // Calculate left and right path sum (if they are negative, don't take them)
    int leftPathSum = Math.max(0, findMaxPathSum(root.left));
    int rightPathSum = Math.max(0, findMaxPathSum(root.right));

    // Just in case current root is the root of the maxPath,
    // Calculate the current path (left+root+right) sum, and update maxSum
    int currPathSum = root.val + leftPathSum + rightPathSum;
    maxSum = Math.max(maxSum, currPathSum);

    // Return the root + either taking left or right path (as we can't take both
    // left and right since we need a linear path), to be used by the calling method
    return root.val + Math.max(leftPathSum, rightPathSum);
  }

  public int maxPathSum(TreeNode root) {
    if (root == null) return currentMaxSum;
    if (root.left == null && root.right == null) return root.val;
    var stn = toStn(root);
    findMaxSum(stn);
    return Math.max(currentMaxSum, allSum);
  }

  private void findMaxSum(SumTreeNode root) {
    if (root == null) return;
    var localMax =
        Stream.of(
                root.val,
                root.sumLeft,
                root.sumRight,
                Math.max(0, root.sumLeft) + root.val + Math.max(0, root.sumRight))
            .mapToInt(a -> a)
            .max()
            .orElseThrow();
    currentMaxSum = Math.max(currentMaxSum, localMax);
    findMaxSum(root.left);
    findMaxSum(root.right);
  }

  private SumTreeNode toStn(TreeNode root) {
    var stn = new SumTreeNode(root.val);
    allSum += root.val;
    if (root.left != null) {
      var stnLeft = toStn(root.left);
      stn.left = stnLeft;
      stn.sumLeft = Math.max(stnLeft.sumLeft, 0) + stnLeft.val + Math.max(stnLeft.sumRight, 0);
    }
    if (root.right != null) {
      var stnRight = toStn(root.right);
      stn.right = stnRight;
      stn.sumRight = Math.max(stnRight.sumLeft, 0) + stnRight.val + Math.max(stnRight.sumRight, 0);
    }
    return stn;
  }

  private static class SumTreeNode {
    int val;
    SumTreeNode left;
    SumTreeNode right;
    int sumLeft = MIN_MIN;
    int sumRight = MIN_MIN;

    SumTreeNode(int val) {
      this.val = val;
    }

    SumTreeNode(int val, SumTreeNode left, SumTreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public static void main(String[] args) {
    System.out.println(
        new MaxPathSum()
            .maxPathSum(
                listToRoot(
                    Stream.of(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1)
                        .collect(Collectors.toList()))));
  }

  private static TreeNode listToRoot(List<Integer> vals) {
    var nodes = new ArrayList<TreeNode>(vals.size());
    for (int i = 0, valsSize = vals.size(); i < valsSize; i++) {
      var val = vals.get(i);
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
