package algo.easy

private enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

private fun changeDirection(direction: Direction): Direction = when (direction) {
    Direction.UP -> Direction.RIGHT
    Direction.DOWN -> Direction.LEFT
    Direction.LEFT -> Direction.UP
    Direction.RIGHT -> Direction.DOWN
}

private fun nextDirection(row: Int, col: Int, direction: Direction, matrix: Array<IntArray>, circle: Int): Direction =
    when (direction) {
        Direction.UP -> if (row == circle + 1) changeDirection(direction) else direction
        Direction.DOWN -> if (row == matrix.size - circle - 1) changeDirection(direction) else direction
        Direction.LEFT -> if (col == circle) changeDirection(direction) else direction
        Direction.RIGHT -> if (col == matrix[0].size - circle - 1) changeDirection(direction) else direction
    }

private fun nextRow(row: Int, direction: Direction): Int = when (direction) {
    Direction.UP -> row - 1
    Direction.DOWN -> row + 1
    Direction.LEFT -> row
    Direction.RIGHT -> row
}

private fun nextCol(col: Int, direction: Direction): Int = when (direction) {
    Direction.UP -> col
    Direction.DOWN -> col
    Direction.LEFT -> col - 1
    Direction.RIGHT -> col + 1
}

private fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val elementsCount = matrix.size * matrix[0].size

    val ans = mutableListOf<Int>()
    var circle = 0
    var row = circle
    var col = circle
    var direction = Direction.RIGHT

    while (ans.size < elementsCount) {
        ans.add(matrix[row][col])
        val wasUp = direction == Direction.UP
        direction = nextDirection(row, col, direction, matrix, circle)
        if (wasUp && direction == Direction.RIGHT) ++circle
        row = nextRow(row, direction)
        col = nextCol(col, direction)
    }

    return ans
}

fun main() {
    spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
}