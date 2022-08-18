package algo.graph

import java.util.*

private fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
    if (grid[0][0] == 1 || grid.last().last() == 1) return -1

    val width = grid[0].size
    val height = grid.size

    val visited = Array(grid.size) { BooleanArray(grid[0].size) { false } }
    val distances = Array(grid.size) { IntArray(grid[0].size) { 10_000 } }.also { it[0][0] = 1 }
    val pq = PriorityQueue<Triple<Int, Int, Int>> { (_, _, dist1), (_, _, dist2) -> dist1.compareTo(dist2) }
        .apply { add(Triple(0, 0, 1)) }

    fun shouldVisit(row: Int, col: Int, dist: Int): Boolean =
        row in 0 until height
                && col in 0 until width
                && grid[row][col] == 0
                && !visited[row][col]
                && dist < distances[row][col]

    fun tryVisit(row: Int, col: Int, dist: Int) {
        if (shouldVisit(row, col, dist + 1)) {
            distances[row][col] = dist + 1
            pq.add(Triple(row, col, dist + 1))
        }
    }

    while (pq.isNotEmpty()) {
        val (row, col, distance) = pq.poll()
        visited[row][col] = true
        if (distances[row][col] < distance) continue

        tryVisit(row - 1, col - 1, distance)
        tryVisit(row - 1, col, distance)
        tryVisit(row - 1, col + 1, distance)
        tryVisit(row, col + 1, distance)
        tryVisit(row + 1, col + 1, distance)
        tryVisit(row + 1, col, distance)
        tryVisit(row + 1, col - 1, distance)
        tryVisit(row, col - 1, distance)
    }

    val distance = distances.last().last()
    return if (distance == 10_000) -1 else distance
}

fun main() {
    println(shortestPathBinaryMatrix(arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 0), intArrayOf(1, 1, 0))))
}