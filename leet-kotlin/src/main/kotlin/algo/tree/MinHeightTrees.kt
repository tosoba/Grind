package algo.tree

import java.util.*
import kotlin.math.max

private fun findMinHeightTreesDFSFromAll(n: Int, edges: Array<IntArray>): List<Int> {
    val adj = List(n) { mutableListOf<Int>() }
    edges.forEach { (n1, n2) ->
        adj[n1].add(n2)
        adj[n2].add(n1)
    }

    var minHeight = Integer.MAX_VALUE
    var minHeightRoots = mutableListOf<Int>()
    repeat(n) { startNode ->
        var totalHeight = 0
        val toVisit = Stack<Pair<Int, Int>>()
        toVisit.push(startNode to 0)
        val visited = mutableSetOf<Int>()
        while (toVisit.isNotEmpty()) {
            val (node, height) = toVisit.pop()
            totalHeight = max(totalHeight, height)
            if (totalHeight > minHeight) break
            if (visited.contains(node)) continue

            visited.add(node)
            adj[node].forEach { toVisit.add(it to height + 1) }
        }

        if (totalHeight < minHeight) {
            minHeight = totalHeight
            minHeightRoots = mutableListOf(startNode)
        } else if (totalHeight == minHeight) {
            minHeightRoots.add(startNode)
        }
    }

    return minHeightRoots
}

private fun findMinHeightTreesTopSort(n: Int, edges: Array<IntArray>): List<Int> {
    if (n == 0) return emptyList()
    if (n == 1) return mutableListOf(0)

    val adj = List(n) { mutableListOf<Int>() }
    val degrees = Array(n) { 0 }
    edges.forEach { (n1, n2) -> // construct adj list + degrees array for all nodes (undirected so it goes both ways)
        adj[n1].add(n2)
        adj[n2].add(n1)
        ++degrees[n1]
        ++degrees[n2]
    }

    // add all "initial" leaves to toVisit
    val toVisit = ArrayDeque<Int>()
    degrees.forEachIndexed { index, degree ->
        if (degree == 1) {
            toVisit.addLast(index)
        }
    }

    val ans = mutableListOf<Int>()
    while (toVisit.isNotEmpty()) {
        ans.clear()
        val leavesCount = toVisit.size
        // poll all "current" leaves in one iteration while keeping track of "latest" leaves in ans
        // cannot poll leaves one by one because it's impossible to keep track of "latest" leaves then
        // since there can be either 1 or 2 roots with min height
        for (i in leavesCount downTo 1) {
            val node = toVisit.pollFirst()
            ans.add(node)
            adj[node].forEach { if (--degrees[it] == 1) toVisit.addLast(it) }
        }
    }
    return ans
}

fun main() {
    val ans = findMinHeightTreesTopSort(
        6,
        arrayOf(intArrayOf(3, 0), intArrayOf(3, 1), intArrayOf(3, 2), intArrayOf(3, 4), intArrayOf(5, 4))
    )
}