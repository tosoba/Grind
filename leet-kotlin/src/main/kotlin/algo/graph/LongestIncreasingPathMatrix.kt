package algo.graph

import java.util.*
import kotlin.math.max

private fun longestIncreasingPath(matrix: Array<IntArray>): Int {
    val width = matrix[0].size
    val height = matrix.size

    val pq = PriorityQueue<Triple<Int, Int, Int>> { o1, o2 -> o1.third.compareTo(o2.third) }
    matrix.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, colValue ->
            pq.add(Triple(rowIndex, colIndex, colValue))
        }
    }

    val longestPathsEndingAt = Array(matrix.size) { IntArray(matrix[0].size) { 1 } }
    var longestPath = 1
    while (pq.isNotEmpty()) {
        val (row, col, value) = pq.poll()
        var longestPathEndingAtNeighbour = 0
        if (row - 1 >= 0 && matrix[row - 1][col] < value) {
            longestPathEndingAtNeighbour = max(longestPathEndingAtNeighbour, longestPathsEndingAt[row - 1][col])
        }
        if (row + 1 < height && matrix[row + 1][col] < value) {
            longestPathEndingAtNeighbour = max(longestPathEndingAtNeighbour, longestPathsEndingAt[row + 1][col])
        }
        if (col - 1 >= 0 && matrix[row][col - 1] < value) {
            longestPathEndingAtNeighbour = max(longestPathEndingAtNeighbour, longestPathsEndingAt[row][col - 1])
        }
        if (col + 1 < width && matrix[row][col + 1] < value) {
            longestPathEndingAtNeighbour = max(longestPathEndingAtNeighbour, longestPathsEndingAt[row][col + 1])
        }

        longestPathsEndingAt[row][col] = longestPathEndingAtNeighbour + 1
        longestPath = max(longestPath, longestPathsEndingAt[row][col])
    }
    return longestPath
}

fun main() {
    longestIncreasingPath(arrayOf(intArrayOf(9, 9, 4), intArrayOf(6, 6, 8), intArrayOf(2, 1, 1)))
}