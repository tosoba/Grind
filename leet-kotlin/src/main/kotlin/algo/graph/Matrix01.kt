package algo.graph

import java.util.*

private fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
    val height = mat.size
    val width = mat[0].size

    val cells = ArrayDeque<Triple<Int, Int, Int>>()
    mat.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, colValue ->
            if (colValue == 0) {
                cells.addLast(Triple(rowIndex, colIndex, 0))
            }
        }
    }

    val visited = mutableSetOf<Pair<Int, Int>>()
    while (cells.isNotEmpty()) {
        val (row, col, dist) = cells.pollFirst()
        val cell = row to col
        if (visited.contains(cell)) continue

        mat[row][col] = Integer.max(mat[row][col], dist)
        visited.add(cell)

        if (row - 1 >= 0 && mat[row - 1][col] != 0) {
            cells.addLast(Triple(row - 1, col, dist + 1))
        }
        if (row + 1 < height && mat[row + 1][col] != 0) {
            cells.addLast(Triple(row + 1, col, dist + 1))
        }
        if (col - 1 >= 0 && mat[row][col - 1] != 0) {
            cells.addLast(Triple(row, col - 1, dist + 1))
        }
        if (col + 1 < width && mat[row][col + 1] != 0) {
            cells.addLast(Triple(row, col + 1, dist + 1))
        }
    }

    return mat
}
