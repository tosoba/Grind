package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return splitWalk(root.left, root.right);
    }

    private boolean splitWalk(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left != null ^ right != null) return false;
        return left.val == right.val && splitWalk(left.left, right.right) && splitWalk(left.right, right.left);
    }

    public static void main(String[] args) {
        System.out.println(
                new IsSymmetric().isSymmetric(listToRoot(Stream.of(1, 2, 2, 3, 4, 4, 3, 5, 6, 7, 8, 8, 7, 6, 5).collect(Collectors.toList()))));
    }

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
