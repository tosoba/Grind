package leetcode.rand_arithmetic

import java.util.*

private fun twoSum(numbers: IntArray, target: Int): IntArray {
    numbers.forEachIndexed { index, num ->
        if (num > target) return intArrayOf()
        val other = target - num
        val otherIndex = Arrays.binarySearch(numbers, other)
        if (otherIndex >= 0) {
            if (otherIndex != index) {
                val result = intArrayOf(index + 1, otherIndex + 1)
                result.sort()
                return result
            } else {
                if (otherIndex + 1 < numbers.size && numbers[otherIndex + 1] + num == target) {
                    val result = intArrayOf(index + 1, otherIndex + 2)
                    result.sort()
                    return result
                } else if (otherIndex - 1 < numbers.size && numbers[otherIndex - 1] + num == target) {
                    val result = intArrayOf(index + 1, otherIndex)
                    result.sort()
                    return result
                }
            }
        }
    }
    return intArrayOf()
}

fun main() {
    println(twoSum(intArrayOf(-1, 0), -1).contentToString())
}