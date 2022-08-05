package algo.dp

private fun minimumTotal(triangle: List<List<Int>>): Int {
    var prevRow = mutableListOf(triangle[0][0])

    for (rowIndex in 1 until triangle.size) {
        val nextRow = mutableListOf<Int>()
        triangle[rowIndex].forEachIndexed { colIndex, colVal ->
            nextRow.add(
                when (colIndex) {
                    0 -> prevRow[0]
                    triangle[rowIndex].size - 1 -> prevRow.last()
                    else -> Integer.min(prevRow[colIndex - 1], prevRow[colIndex])
                } + colVal
            )
        }
        prevRow = nextRow
    }

    return prevRow.min()!!
}