package algo.interval

import java.util.*
import kotlin.math.max
import kotlin.math.min

private fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    val result = LinkedList<IntArray>()
    var i = 0
    while (i < intervals.size && intervals[i][1] < newInterval[0]) {
        result.add(intervals[i++])
    }

    var overlapping = newInterval
    while (i < intervals.size && intervals[i][0] <= newInterval[1]) {
        overlapping = intArrayOf(
            min(newInterval[0], intervals[i][0]),
            max(newInterval[1], intervals[i][1])
        )
        ++i
    }
    result.add(overlapping)

    while (i < intervals.size) {
        result.add(intervals[i++])
    }

    return result.toArray(arrayOf())
}

fun main() {
//    println(
//        insert(
//            arrayOf(intArrayOf(1, 2), intArrayOf(3, 5), intArrayOf(6, 7), intArrayOf(8, 10), intArrayOf(12, 16)),
//            intArrayOf(4, 8)
//        ).contentToString()
//    )

    val ans = insert(
        arrayOf(intArrayOf(1, 5), intArrayOf(6, 8)),
        intArrayOf(5, 6)
    )
}