package algo.tree

import java.util.*

private class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

private fun connect(root: Node?): Node? {
    if (root?.left == null && root?.right == null) return root

    var prev: Pair<Node, Int>? = null
    val nodes = ArrayDeque<Pair<Node, Int>>().apply {
        root.left?.let { addLast(it to 1) }
        root.right?.let { addLast(it to 1) }
    }

    while (nodes.isNotEmpty()) {
        val next = nodes.pollFirst()
        val (node, height) = next
        if (prev != null) {
            prev.first.next = if (prev.second < height) null else node
        }
        prev = next

        node.left?.let { nodes.addLast(it to height + 1) }
        node.right?.let { nodes.addLast(it to height + 1) }
    }

    return root
}

fun main() {

}
