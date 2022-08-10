package algo.graph

import java.util.*

private fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    val adj = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
    times.forEach { (source, dest, time) ->
        adj[source]?.add(dest to time) ?: run { adj[source] = mutableListOf(dest to time) }
    }

    val pq = PriorityQueue<Pair<Int, Int>> { (_, time1), (_, time2) -> time1.compareTo(time2) }
        .apply { add(k to 0) }
    val visited = BooleanArray(n)
    val minTimes = IntArray(n) { if (it == k - 1) 0 else Int.MAX_VALUE }

    while (pq.isNotEmpty()) {
        val (node, minTime) = pq.poll()
        visited[node - 1] = true
        if (minTimes[node - 1] < minTime) continue

        adj[node]?.forEach { next ->
            val (dest, time) = next
            if (visited[dest - 1] || minTimes[dest - 1] < minTime + time) return@forEach
            minTimes[dest - 1] = minTime + time
            pq.add(dest to minTime + time)
        }
    }

    val delayTime = minTimes.max()!!
    return if (delayTime == Int.MAX_VALUE) -1 else delayTime
}

fun main() {
    networkDelayTime(arrayOf(intArrayOf(2, 1, 1), intArrayOf(2, 3, 1), intArrayOf(3, 4, 1)), 4, 2)
}