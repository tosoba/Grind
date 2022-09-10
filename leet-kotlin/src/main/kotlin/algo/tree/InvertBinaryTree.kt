package algo.tree

private fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return root
    swapChildren(root)
    invertTree(root.left)
    invertTree(root.right)
    return root
}

private fun swapChildren(root: TreeNode?) {
    if (root == null) return
    val tmp = root.right
    root.right = root.left
    root.left = tmp
}