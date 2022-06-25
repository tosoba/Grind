package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelOrder {

  private List<List<Integer>> buildResult(TreeNode root, int level, List<List<Integer>> result) {
    if (root == null) return result;
    if (result.size() == level) {
      var values = new ArrayList<Integer>();
      values.add(root.val);
      result.add(values);
    } else {
      result.get(level).add(root.val);
    }
    buildResult(root.left, level + 1, result);
    buildResult(root.right, level + 1, result);
    return result;
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    return buildResult(root, 0, new ArrayList<>());
  }

  public List<Double> averageOfLevels(TreeNode root) {
    var result = buildResult(root, 0, new ArrayList<>());
    return result.stream()
        .map(level -> level.stream().mapToDouble(Double::valueOf).average().getAsDouble())
        .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    var root = listToRoot(Stream.of(3, 9, 20, null, null, 15, 7).collect(Collectors.toList()));
    System.out.println(new LevelOrder().levelOrder(root));
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
