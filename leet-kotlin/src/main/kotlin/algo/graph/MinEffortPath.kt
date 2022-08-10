package algo.graph

import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

private fun minimumEffortPathBFS(heights: Array<IntArray>): Int {
    val width = heights[0].size
    val height = heights.size
    val goal = height - 1 to width - 1

    val routes = PriorityQueue<Route> { r1, r2 -> r1.maxEffort.compareTo(r2.maxEffort) }
    routes.add(Route(linkedSetOf(0 to 0), 0))

    var minEffort = Int.MAX_VALUE
    val minEfforts = Array(heights.size) { IntArray(heights[0].size) { Int.MAX_VALUE } }

    while (routes.isNotEmpty()) {
        val (visited, maxEffortSoFar) = routes.poll()
        if (maxEffortSoFar > minEffort) continue

        val cell = visited.last()
        if (cell == goal) {
            minEffort = min(minEffort, maxEffortSoFar)
            continue
        }

        val (row, col) = cell
        if (minEfforts[row][col] <= maxEffortSoFar) continue
        minEfforts[row][col] = maxEffortSoFar

        if (row - 1 >= 0
            && !visited.contains(row - 1 to col)
            && max(maxEffortSoFar, abs(heights[row][col] - heights[row - 1][col])) < minEfforts[row - 1][col]
        ) {
            routes.add(
                Route(
                    LinkedHashSet(visited).apply { add(row - 1 to col) },
                    max(maxEffortSoFar, abs(heights[row][col] - heights[row - 1][col]))
                )
            )
        }

        if (row + 1 < height
            && !visited.contains(row + 1 to col)
            && max(maxEffortSoFar, abs(heights[row][col] - heights[row + 1][col])) < minEfforts[row + 1][col]
        ) {
            val next = row + 1 to col
            val effort = max(maxEffortSoFar, abs(heights[row][col] - heights[row + 1][col]))
            if (next == goal) {
                minEffort = min(minEffort, effort)
            } else {
                routes.add(
                    Route(
                        LinkedHashSet(visited).apply { add(next) },
                        effort
                    )
                )
            }
        }

        if (col - 1 >= 0
            && !visited.contains(row to col - 1)
            && max(maxEffortSoFar, abs(heights[row][col] - heights[row][col - 1])) < minEfforts[row][col - 1]
        ) {
            routes.add(
                Route(
                    LinkedHashSet(visited).apply { add(row to col - 1) },
                    max(maxEffortSoFar, abs(heights[row][col] - heights[row][col - 1]))
                )
            )
        }

        if (col + 1 < width
            && !visited.contains(row to col + 1)
            && max(maxEffortSoFar, abs(heights[row][col] - heights[row][col + 1])) < minEfforts[row][col + 1]
        ) {
            val next = row to col + 1
            val effort = max(maxEffortSoFar, abs(heights[row][col] - heights[row][col + 1]))
            if (next == goal) {
                minEffort = min(minEffort, effort)
            } else {
                routes.add(
                    Route(
                        LinkedHashSet(visited).apply { add(next) },
                        effort
                    )
                )
            }
        }
    }

    return minEffort
}

private fun minimumEffortPathDjikstra(heights: Array<IntArray>): Int {
    val width = heights[0].size
    val height = heights.size

    val pq = PriorityQueue<Triple<Int, Int, Int>> { (_, _, minEff1), (_, _, minEff2) -> minEff1.compareTo(minEff2) }
        .apply { add(Triple(0, 0, 0)) }
    val minEfforts =
        Array(heights.size) { IntArray(heights[0].size) { 10.0.pow(6.0).toInt() } } // analogous do dist table
    minEfforts[0][0] = 0
    val visited = Array(heights.size) { BooleanArray(heights[0].size) { false } }
    while (pq.isNotEmpty()) {
        val (row, col, minEffort) = pq.poll()
        visited[row][col] = true
        if (minEfforts[row][col] < minEffort) continue

        if (row - 1 >= 0
            && !visited[row - 1][col]
            && minEfforts[row - 1][col] > max(abs(heights[row][col] - heights[row - 1][col]), minEfforts[row][col])
        ) {
            minEfforts[row - 1][col] = max(abs(heights[row][col] - heights[row - 1][col]), minEfforts[row][col])
            // it's CRUCIAL to update minEfforts when cell is just being added to the queue (before its ever polled).
            // only when it's polled eventually - cell should be marked as visited
            pq.add(Triple(row - 1, col, max(abs(heights[row][col] - heights[row - 1][col]), minEfforts[row][col])))
        }

        if (row + 1 < height
            && !visited[row + 1][col]
            && minEfforts[row + 1][col] > max(abs(heights[row][col] - heights[row + 1][col]), minEfforts[row][col])
        ) {
            minEfforts[row + 1][col] = max(abs(heights[row][col] - heights[row + 1][col]), minEfforts[row][col])
            pq.add(Triple(row + 1, col, max(abs(heights[row][col] - heights[row + 1][col]), minEfforts[row][col])))
        }

        if (col - 1 >= 0
            && !visited[row][col - 1]
            && minEfforts[row][col - 1] > max(abs(heights[row][col] - heights[row][col - 1]), minEfforts[row][col])
        ) {
            minEfforts[row][col - 1] = max(abs(heights[row][col] - heights[row][col - 1]), minEfforts[row][col])
            pq.add(Triple(row, col - 1, max(abs(heights[row][col] - heights[row][col - 1]), minEfforts[row][col])))
        }

        if (col + 1 < width
            && !visited[row][col + 1]
            && minEfforts[row][col + 1] > max(abs(heights[row][col] - heights[row][col + 1]), minEfforts[row][col])
        ) {
            minEfforts[row][col + 1] = max(abs(heights[row][col] - heights[row][col + 1]), minEfforts[row][col])
            pq.add(Triple(row, col + 1, max(abs(heights[row][col] - heights[row][col + 1]), minEfforts[row][col])))
        }
    }

    return minEfforts.last().last()
}

private data class Route(
    val visited: LinkedHashSet<Pair<Int, Int>>,
    val maxEffort: Int
)

fun main() {
    minimumEffortPathDjikstra(
        arrayOf(
            intArrayOf(8, 3, 2, 5, 2, 10, 7, 1, 8, 9),
            intArrayOf(1, 4, 9, 1, 10, 2, 4, 10, 3, 5),
            intArrayOf(4, 10, 10, 3, 6, 1, 3, 9, 8, 8),
            intArrayOf(4, 4, 6, 10, 10, 10, 2, 10, 8, 8),
            intArrayOf(9, 10, 2, 4, 1, 2, 2, 6, 5, 7),
            intArrayOf(2, 9, 2, 6, 1, 4, 7, 6, 10, 9),
            intArrayOf(8, 8, 2, 10, 8, 2, 3, 9, 5, 3),
            intArrayOf(2, 10, 9, 3, 5, 1, 7, 4, 5, 6),
            intArrayOf(2, 3, 9, 2, 5, 10, 2, 7, 1, 8),
            intArrayOf(9, 10, 4, 10, 7, 4, 9, 3, 1, 6)
        )
    )
}