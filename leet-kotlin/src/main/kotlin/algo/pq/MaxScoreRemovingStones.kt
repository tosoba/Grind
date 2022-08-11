package algo.pq

import java.util.*

private fun maximumScore(a: Int, b: Int, c: Int): Int {
    val piles = PriorityQueue<Int> { o1, o2 -> -1 * o1.compareTo(o2) }.apply {
        add(a)
        add(b)
        add(c)
    }

    var points = 0
    while (piles.count { it > 0 } >= 2) {
        val min = piles.last { it > 0 }
        val max = piles.poll()
        piles.remove(min)
        piles.add(min - 1)
        piles.add(max - 1)
        ++points
    }
    return points
}

fun main() {
    maximumScore(4, 4, 6)
}