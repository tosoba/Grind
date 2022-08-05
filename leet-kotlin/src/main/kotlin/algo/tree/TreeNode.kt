package algo.tree

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun listToRoot(vals: List<Int?>): TreeNode? {
    val nodes = ArrayList<TreeNode?>(vals.size)
    var i = 0
    val valsSize = vals.size

    while (i < valsSize) {
        val `val` = vals[i]
        if (`val` == null) {
            nodes.add(null)
            i++
            continue
        }

        val node = TreeNode(`val`)
        nodes.add(node)
        if (i == 0) {
            i++
            continue
        }

        if ((i - 1) % 2 == 0) {
            nodes[(i - 1) / 2]!!.left = node
        } else {
            nodes[(i - 2) / 2]!!.right = node
        }

        i++
    }

    return nodes[0]
}