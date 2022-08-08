package algo.graph

private fun findRedundantConnectionUnionFind(edges: Array<IntArray>): IntArray {
    val roots = IntArray(edges.size) { it + 1 }

    fun rootOf(node: Int): Int =
        if (roots[node - 1] == node) node
        else rootOf(roots[node - 1])

    var ans = intArrayOf()

    fun union(node1: Int, node2: Int) {
        val root1 = rootOf(node1)
        val root2 = rootOf(node2)
        if (root1 != root2) {
            roots[root2 - 1] = root1
        } else {
            ans = intArrayOf(node1, node2)
        }
    }

    edges.forEach { (node1, node2) -> union(node1, node2) }

    return ans
}

fun main() {
    findRedundantConnectionUnionFind(
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(1, 4),
            intArrayOf(1, 5)
        )
    )

    findRedundantConnectionUnionFind(
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3)
        )
    )
}
