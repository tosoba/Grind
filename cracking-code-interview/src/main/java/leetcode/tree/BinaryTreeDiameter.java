package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTreeDiameter {
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

    private int maxDepth = 0;

    private int maxDepthFrom(TreeNode root) {
        if (root == null) return 0;
        var depthLeft = maxDepthFrom(root.left) + 1;
        var depthRight = maxDepthFrom(root.right) + 1;
        int localDepth = Math.max(depthLeft, depthRight);
        maxDepth = Math.max(localDepth, maxDepth);
        return localDepth;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        maxDepthFrom(root.left);
        maxDepthFrom(root.right);
        return maxDepth + 1;
    }

    private int maxDiameter = 0;

    private int diameterFrom(TreeNode root) {
        if (root == null) return 0;
        var depthLeft = diameterFrom(root.left);
        var depthRight = diameterFrom(root.right);
        int localDiameter = depthLeft + depthRight;
        maxDiameter = Math.max(localDiameter, maxDiameter);
        return Math.max(depthLeft, depthRight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return 0;
        diameterFrom(root);
        return maxDiameter;
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

    public static void main(String[] args) {
        var test = new BinaryTreeDiameter();
        var root = test.listToRoot(Stream.of(3, 1, null, null, 2).collect(Collectors.toList()));
        System.out.println(test.diameterOfBinaryTree(root));
//        var root2 = test.listToRoot(Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList()));
//        System.out.println(test.diameterOfBinaryTree(root2));
//        var res = test.maxDepth(test.listToRoot(Stream.of(3,9,20,null,null,15,7).collect(Collectors.toList())));
//        System.out.println(res);
    }
}
