package algo.graph

import java.util.*

private fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    val height = grid.size
    val width = grid[0].size

    var maxArea = 0
    val visited = mutableSetOf<Pair<Int, Int>>()
    grid.forEachIndexed { rowIndex, gridRow ->
        gridRow.forEachIndexed { colIndex, colValue ->
            if (colValue == 1) {
                val one = rowIndex to colIndex
                if (!visited.contains(one)) {
                    val cells = Stack<Pair<Int, Int>>()
                    cells.add(one)
                    var area = 0
                    while (cells.isNotEmpty()) {
                        val cell = cells.pop()
                        if (visited.contains(cell)) continue

                        maxArea = Integer.max(++area, maxArea)
                        visited.add(cell)

                        val (row, col) = cell
                        if (row - 1 >= 0 && grid[row - 1][col] != 0) {
                            cells.add(Pair(row - 1, col))
                        }
                        if (row + 1 < height && grid[row + 1][col] != 0) {
                            cells.add(Pair(row + 1, col))
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] != 0) {
                            cells.add(Pair(row, col - 1))
                        }
                        if (col + 1 < width && grid[row][col + 1] != 0) {
                            cells.add(Pair(row, col + 1))
                        }
                    }
                }
            }
        }
    }

    return maxArea
}