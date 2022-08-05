package algo.tree

private fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if (root1 == null && root2 == null) return null
    if (root1 == null) return root2
    if (root2 == null) return root1
    root1.`val` += root2.`val`
    root1.left = mergeTrees(root1.left, root2.left)
    root1.right = mergeTrees(root1.right, root2.right)
    return root1
}

fun main() {
    val res = mergeTrees(listToRoot(listOf(1, 3, 2, 5)), listToRoot(listOf(2, 1, 3, null, 4, null, 7)))
}