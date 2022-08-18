package algo.graph

private fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
    val paths = mutableListOf<MutableList<Int>>()
    graph[0].forEach { dest -> paths.add(mutableListOf(0, dest)) }
    buildPaths(graph, paths)
    return paths
}

private fun buildPaths(
    graph: Array<IntArray>,
    currentPaths: MutableList<MutableList<Int>>
) {
    if (currentPaths.all { it.last() == graph.size - 1 }) return

    val indicesToRemove = mutableListOf<Int>()
    currentPaths.indices.forEach { index ->
        val currentNode = currentPaths[index].last()
        if (currentNode == graph.size - 1) return@forEach
        if (graph[currentNode].isEmpty()) {
            indicesToRemove.add(index)
            return@forEach
        }

        currentPaths[index].add(graph[currentNode][0])
        for (i in 1 until graph[currentNode].size) {
            currentPaths.add(currentPaths[index].dropLast(1).toMutableList().apply { add(graph[currentNode][i]) })
        }
    }

    indicesToRemove.forEach { currentPaths.removeAt(it) }

    buildPaths(graph, currentPaths)
}

fun main() {
    println(
        allPathsSourceTarget(
            arrayOf(
                intArrayOf(4, 3, 1),
                intArrayOf(3, 2, 4),
                intArrayOf(),
                intArrayOf(4),
                intArrayOf()
            )
        )
    )
}