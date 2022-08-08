package algo.graph

import kotlin.math.abs

private fun minCostConnectPoints(points: Array<IntArray>): Int {
    val distances = mutableListOf<Triple<Int, Int, Int>>()
    for (i in points.indices) {
        val pointI = points[i]
        for (j in 0 until i) {
            val pointJ = points[j]
            val distance = abs(pointI[0] - pointJ[0]) + abs(pointI[1] - pointJ[1])
            distances.add(Triple(i, j, distance))
        }
    }

    distances.sortBy(Triple<Int, Int, Int>::third)

    val connections = IntArray(points.size) { it }

    fun connectionOf(index: Int): Int =
        if (connections[index] == index) index
        else connectionOf(connections[index])

    fun union(i: Int, j: Int): Boolean {
        val rootI = connectionOf(i)
        val rootJ = connectionOf(j)
        if (rootI == rootJ) return false
        connections[rootJ] = rootI
        return true
    }

    var ans = 0
    var edgesCount = 0
    distances.forEach { triple ->
        val (i, j, dist) = triple
        if (union(i, j)) {
            ans += dist
            if (++edgesCount == points.size - 1) return ans
        }
    }
    return ans
}

fun main() {
    println(
        minCostConnectPoints(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(2, 2),
                intArrayOf(3, 10),
                intArrayOf(5, 2),
                intArrayOf(7, 0)
            )
        )
    )

//    minCostConnectPoints(arrayOf(intArrayOf(2, -3), intArrayOf(-17, -8), intArrayOf(13, 8), intArrayOf(-17, -15)))
}