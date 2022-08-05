package algo.graph

import java.util.*

private fun orangesRotting(grid: Array<IntArray>): Int {
    val width = grid[0].size
    val height = grid.size

    var freshCount = 0
    val cells = ArrayDeque<Triple<Int, Int, Int>>()
    val visited = mutableSetOf<Pair<Int, Int>>()
    var initialRottenCount = 0
    grid.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, cell ->
            if (cell == 2) {
                initialRottenCount++
                cells.addLast(Triple(rowIndex, colIndex, 0))
            } else if (cell == 1) {
                freshCount++
            }
        }
    }

    if (freshCount == 0) return 0

    var minutes = 0
    while (cells.isNotEmpty()) {
        val (row, col, index) = cells.pollFirst()
        val cell = row to col
        if (visited.contains(cell)) continue

        if (grid[row][col] == 1) {
            grid[row][col] = 2
            --freshCount
        }
        visited.add(cell)

        if (row - 1 >= 0 && grid[row - 1][col] == 1) {
            cells.addLast(Triple(row - 1, col, index + 1))
        }
        if (row + 1 < height && grid[row + 1][col] == 1) {
            cells.addLast(Triple(row + 1, col, index + 1))
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 1) {
            cells.addLast(Triple(row, col - 1, index + 1))
        }
        if (col + 1 < width && grid[row][col + 1] == 1) {
            cells.addLast(Triple(row, col + 1, index + 1))
        }

        minutes = index
    }

    return if (freshCount == 0) minutes else -1
}