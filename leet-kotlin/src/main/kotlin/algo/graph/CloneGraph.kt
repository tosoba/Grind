package algo.graph

import kotlin.collections.ArrayList

private class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}

private fun cloneGraph(node: Node?): Node? {
    return cloneGraph(node, mutableMapOf())
}

private fun cloneGraph(node: Node?, visited: MutableMap<Int, Node?>): Node? {
    if (node == null) return null
    if (visited.containsKey(node.`val`)) return visited[node.`val`]

    val copy = Node(node.`val`)
        .apply {
            if (node.neighbors.size > 0) {
                neighbors = ArrayList(node.neighbors.size)
            }
        }
    visited[node.`val`] = copy
    node.neighbors.forEach { copy.neighbors.add(cloneGraph(it, visited)) }

    return copy
}