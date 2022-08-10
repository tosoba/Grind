package algo.backtrack

// recursive DFS with backtracking -> recursive is way easier here (due to the need to backtrack) vs. iterative (not sure if iterative is even possible - it may be but would be super complicated to implement/hard to read/understand)
private fun exist(board: Array<CharArray>, word: String): Boolean {
    val firstLetter = word.first()
    val visited = Array(board.size) { BooleanArray(board[0].size) }
    board.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, letter ->
            if (letter == firstLetter) {
                if (exist(board, word, visited.apply { this[rowIndex][colIndex] = true }, 1, rowIndex, colIndex)) {
                    return true
                }
                visited[rowIndex][colIndex] = false
            }
        }
    }
    return false
}

private fun exist(
    board: Array<CharArray>,
    word: String,
    visited: Array<BooleanArray>,
    index: Int,
    row: Int,
    col: Int
): Boolean {
    if (index == word.length) return true

    val letter = word.elementAt(index)

    if (row - 1 >= 0 && !visited[row - 1][col] && board[row - 1][col] == letter) {
        val result = exist(board, word, visited.apply { this[row - 1][col] = true }, index + 1, row - 1, col)
        if (result) return true
        visited[row - 1][col] = false
    }

    if (row + 1 < board.size && !visited[row + 1][col] && board[row + 1][col] == letter) {
        val result = exist(board, word, visited.apply { this[row + 1][col] = true }, index + 1, row + 1, col)
        if (result) return true
        visited[row + 1][col] = false
    }

    if (col - 1 >= 0 && !visited[row][col - 1] && board[row][col - 1] == letter) {
        val result = exist(board, word, visited.apply { this[row][col - 1] = true }, index + 1, row, col - 1)
        if (result) return true
        visited[row][col - 1] = false
    }

    if (col + 1 < board[0].size && !visited[row][col + 1] && board[row][col + 1] == letter) {
        val result = exist(board, word, visited.apply { this[row][col + 1] = true }, index + 1, row, col + 1)
        if (result) return true
        visited[row][col + 1] = false
    }

    return false
}

fun main() {
    println(
        exist(
            arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'C', 'S'), charArrayOf('A', 'D', 'E', 'E')),
            "ABCCED"
        )
    )
    println(
        exist(
            arrayOf(charArrayOf('A', 'B', 'C', 'E'), charArrayOf('S', 'F', 'E', 'S'), charArrayOf('A', 'D', 'E', 'E')),
            "ABCESEEEFS"
        )
    )
}