package algo.two_pointers

import java.util.*
import kotlin.math.max
import kotlin.math.min

private fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
    if (firstList.isEmpty() || secondList.isEmpty()) return emptyArray()
    var fIndex = 0
    var sIndex = 0
    val intersections = LinkedList<IntArray>()
    while (fIndex < firstList.size && sIndex < secondList.size) {
        val (fStart, fEnd) = firstList[fIndex]
        val (sStart, sEnd) = secondList[sIndex]
        val intStart = max(fStart, sStart)
        val intEnd = min(fEnd, sEnd)
        if (intStart <= intEnd) {
            intersections.add(intArrayOf(intStart, intEnd))
        }
        if (fEnd < sEnd) {
            ++fIndex
        } else if (fEnd > sEnd) {
            ++sIndex
        } else {
            ++fIndex
            ++sIndex
        }
    }
    return intersections.toTypedArray()
}