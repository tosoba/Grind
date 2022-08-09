package algo.pq

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    fun distance(index: Int): Double {
        val (x, y) = points[index]
        return sqrt(x.toDouble().pow(2.0) + y.toDouble().pow(2.0))
    }

    val pq = PriorityQueue<Int>(points.size) { i, j ->
        distance(i).compareTo(distance(j))
    }.apply {
        addAll(points.indices)
    }

    val ans = Array(k) { intArrayOf() }
    var i = 0
    while (i < k) {
        ans[i++] = points[pq.poll()]
    }
    return ans
}

fun main() {

}