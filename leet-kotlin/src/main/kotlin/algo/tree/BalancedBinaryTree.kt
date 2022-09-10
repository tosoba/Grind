package algo.tree

import kotlin.math.abs
import kotlin.math.max

private var balanced = true

private fun isBalanced(root: TreeNode?): Boolean {
    if (root == null || root.left == null && root.right == null) return true
    height(root)
    return balanced
}

private fun height(root: TreeNode?): Int {
    if (!balanced) return -1
    if (root == null) return 0
    val hl = height(root.left)
    val hr = height(root.right)
    if (abs(hl - hr) > 1) balanced = false
    return 1 + max(hl, hr)
}

fun main() {
    println(isBalanced(listToRoot(listOf(1, 2, 3, 4, 5, null, 6, 7, null, null, null, null, 8))))
}