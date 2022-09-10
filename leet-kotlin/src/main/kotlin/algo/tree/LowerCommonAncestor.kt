package algo.tree

import kotlin.math.max
import kotlin.math.min

private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null) return null
    return lowestCommonAncestor(root, min(p.`val`, q.`val`), max(p.`val`, q.`val`))
}

private fun lowestCommonAncestor(root: TreeNode?, v1: Int, v2: Int): TreeNode? {
    if (root == null) return null
    if (root.`val` == v1 || root.`val` == v2 || root.`val` in (v1 + 1) until v2) return root

    return if (root.`val` > v2) lowestCommonAncestor(root.left, v1, v2)
    else if (root.`val` < v1) lowestCommonAncestor(root.right, v1, v2)
    else null
}

fun main() {
    println(lowestCommonAncestor(listToRoot(listOf(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5)), TreeNode(2), TreeNode(4)))
}
