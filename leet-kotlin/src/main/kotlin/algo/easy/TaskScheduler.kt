package algo.easy

import java.util.*

private fun leastInterval(tasks: CharArray, n: Int): Int {
    if (n == 0) return tasks.size

    val pq = PriorityQueue<Pair<Char, Int>> { (_, count1), (_, count2) -> count2.compareTo(count1) }

    val lastIndices = mutableMapOf<Char, Int>()
    val counts = mutableMapOf<Char, Int>()
    tasks.forEach { task ->
        lastIndices.putIfAbsent(task, -1)
        counts.merge(task, 1) { a, b -> a + b }
    }
    counts.forEach { (task, count) -> pq.add(task to count) }

    var index = 0
    while (pq.isNotEmpty()) {
        val pair = pq.firstOrNull { (task, _) -> lastIndices[task] == -1 || index - lastIndices[task]!! > n }
        if (pair == null) {
            ++index
            continue
        }

        val (task, count) = pair
        lastIndices[task] = index
        pq.remove(pair)
        if (count - 1 > 0) {
            pq.add(task to count - 1)
        }
        ++index
    }

    return index
}

fun main() {
    println(leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2))
    println(leastInterval(charArrayOf('A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'), 2))
}