package leetcode.rand_arithmetic

import kotlin.math.pow

class SortedSquares {
    fun sortedSquares(nums: IntArray): IntArray {
        if (nums.isEmpty()) return nums

        val result = IntArray(nums.size)
        var indexOfFirstNonNeg = 0
        while (indexOfFirstNonNeg < nums.size && nums[indexOfFirstNonNeg] < 0) {
            ++indexOfFirstNonNeg
        }

        var i = 0
        var negativeIndex = indexOfFirstNonNeg - 1
        var nonNegativeIndex = indexOfFirstNonNeg
        while (negativeIndex >= 0 || nonNegativeIndex < nums.size) {
            val neg = if (negativeIndex >= 0) -1 * nums[negativeIndex] else null
            val nonNeg = if (nonNegativeIndex < nums.size) nums[nonNegativeIndex] else null
            if (neg == null && nonNeg == null) break

            if (nonNeg == null || (neg != null && neg < nonNeg)) {
                result[i++] = neg!!
                --negativeIndex
            } else {
                result[i++] = nonNeg
                ++nonNegativeIndex
            }
        }

        return result.map { it.toDouble().pow(2.0).toInt() }.toIntArray()
    }
}

fun main() {
    val arr = SortedSquares().sortedSquares(intArrayOf(-7, -3))
    arr.forEach { println(it) }
}