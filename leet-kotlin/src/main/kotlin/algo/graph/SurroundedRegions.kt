package algo.graph

import java.util.*

fun solve(board: Array<CharArray>) {
    val width = board[0].size
    val height = board.size

    fun isOnEdge(row: Int, col: Int): Boolean = row == 0 || row == height - 1 || col == 0 || col == width - 1

    val visited = mutableMapOf<Pair<Int, Int>, Boolean>()

    fun dfs(startRow: Int, startCol: Int) {
        val vis = mutableSetOf<Pair<Int, Int>>()
        val cells = Stack<Pair<Int, Int>>().apply { push(startRow to startCol) }
        var shouldFlip = true

        while (cells.isNotEmpty()) {
            val cell = cells.pop()
            if (vis.contains(cell)) continue

            val (row, col) = cell
            if (isOnEdge(row, col)) shouldFlip = false

            vis.add(cell)

            if (row - 1 >= 0 && board[row - 1][col] == 'O') cells.push(row - 1 to col)
            if (row + 1 < height && board[row + 1][col] == 'O') cells.push(row + 1 to col)
            if (col - 1 >= 0 && board[row][col - 1] == 'O') cells.push(row to col - 1)
            if (col + 1 < width && board[row][col + 1] == 'O') cells.push(row to col + 1)
        }

        vis.forEach { (row, col) -> visited[row to col] = shouldFlip }
    }

    board.forEachIndexed { rowIndex, row ->
        row.forEachIndexed inner@{ colIndex, colValue ->
            if (colValue != 'O' || visited.containsKey(rowIndex to colIndex)) return@inner
            dfs(rowIndex, colIndex)
        }
    }

    visited.forEach { (row, col), shouldFlip -> if (shouldFlip) board[row][col] = 'X' }
}

fun main() {

}