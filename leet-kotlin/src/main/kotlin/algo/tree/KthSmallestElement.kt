package algo.tree

private var current = 0
private var value = -1

fun kthSmallest(root: TreeNode?, k: Int): Int {
    go(root, k)
    return value
}

private fun go(root: TreeNode?, k: Int) {
    if (root == null || value > -1) return
    go(root.left, k)

    if (++current == k) {
        value = root.`val`
        return
    }

    return go(root.right, k)
}
