package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestorOptimal(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode right = lowestCommonAncestorOptimal(root.right, p, q);
        TreeNode left = lowestCommonAncestorOptimal(root.left, p, q);
        if (left != null && right != null) return root;
        return right != null ? right : left;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        if (root.val == p.val || root.val == q.val) return root;
        var st = subtreeThatContainsBoth(root, p.val, q.val);
        if (st != null) return st;
        return root;
    }

    private TreeNode result = null;

    private TreeNode subtreeThatContainsBoth(TreeNode root, int val1, int val2) {
        if (result != null) return result;

        if (root == null) return null;

        if (root.left != null && root.right != null) {
            if (root.left.val == val1 && root.right.val == val2) return root;
            if (root.left.val == val2 && root.right.val == val1) return root;
        }

        if (root.val == val1) {
            var left = subtreeContains(root.left, val2);
            if (left) return root;
            var right = subtreeContains(root.right, val2);
            if (right) return root;
        }

        if (root.val == val2) {
            var left = subtreeContains(root.left, val1);
            if (left) return root;
            var right = subtreeContains(root.right, val1);
            if (right) return root;
        }

        if (subtreeContains(root.left, val1) && subtreeContains(root.right, val2) && result == null) {
            result = root;
            return result;
        } else if (subtreeContains(root.left, val2) && subtreeContains(root.right, val1) && result == null) {
            result = root;
            return result;
        }

        var left = subtreeThatContainsBoth(root.left, val1, val2);
        if (left != null) return left;
        return subtreeThatContainsBoth(root.right, val1, val2);
    }

    private boolean subtreeContains(TreeNode root, int val) {
        if (root == null) return false;
        if (root.val == val) return true;
        return subtreeContains(root.left, val) || subtreeContains(root.right, val);
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

    public static void main(String[] args) {
        //[-1,0,3,-2,4,null,null,8]
        //8
        //4
        var root = listToRoot(Stream.of(-1, 0, 3, -2, 4, null, null, 8).collect(Collectors.toList()));
        var lca = new LowestCommonAncestor().lowestCommonAncestor(root, new TreeNode(8), new TreeNode(4));
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
