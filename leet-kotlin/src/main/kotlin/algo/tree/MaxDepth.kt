package algo.tree

import kotlin.math.max

private fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    return maxDepth(root, 1)
}

private fun maxDepth(root: TreeNode?, currentHeight: Int): Int {
    if (root == null || root.left == null && root.right == null) return currentHeight
    if (root.right == null) return maxDepth(root.left, currentHeight + 1)
    if (root.left == null) return maxDepth(root.right, currentHeight + 1)
    return max(maxDepth(root.left, currentHeight + 1), maxDepth(root.right, currentHeight + 1))
}