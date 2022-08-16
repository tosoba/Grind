package algo.graph

private fun numIslands(grid: Array<CharArray>): Int {
    val roots = Array(grid.size * grid[0].size) {
        val row = it / grid[0].size
        val col = it % grid[0].size
        if (grid[row][col] == '1') it
        else -1
    }

    fun to1D(row: Int, col: Int): Int = row * grid[0].size + col

    fun rootOf(index: Int): Int =
        if (roots[index] == index) index
        else rootOf(roots[index])

    fun union(i1: Int, i2: Int) {
        val root1 = rootOf(i1)
        val root2 = rootOf(i2)
        if (root1 == root2) return
        roots[root2] = root1
    }

    grid.forEachIndexed { rowIndex, row ->
        row.forEachIndexed inner@{ colIndex, colValue ->
            if (colValue != '1') return@inner

            if (colIndex - 1 >= 0 && row[colIndex - 1] == '1') {
                union(
                    to1D(rowIndex, colIndex),
                    to1D(rowIndex, colIndex - 1)
                )
            }
            if (rowIndex - 1 >= 0 && grid[rowIndex - 1][colIndex] == '1') {
                union(
                    to1D(rowIndex, colIndex),
                    to1D(rowIndex - 1, colIndex)
                )
            }
            if (colIndex + 1 < row.size && row[colIndex + 1] == '1') {
                union(
                    to1D(rowIndex, colIndex),
                    to1D(rowIndex, colIndex + 1)
                )
            }
            if (rowIndex + 1 < grid.size && grid[rowIndex + 1][colIndex] == '1') {
                union(
                    to1D(rowIndex, colIndex),
                    to1D(rowIndex + 1, colIndex)
                )
            }
        }
    }

    return roots.filter { it != -1 }.map(::rootOf).distinct().count()
}

fun main() {
    println(
        numIslands(
            arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '0', '1', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0')
            )
        )
    )
}