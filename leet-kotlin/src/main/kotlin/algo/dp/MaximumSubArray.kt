package algo.dp

import kotlin.math.max

fun maxSubArray(nums: IntArray): Int {
    val maxSumsEndingAt = IntArray(nums.size) { nums[it] }
    var maxSum = maxSumsEndingAt[0]
    for (index in 1 until nums.size) {
        maxSumsEndingAt[index] = max(maxSumsEndingAt[index], nums[index] + maxSumsEndingAt[index - 1])
        maxSum = max(maxSum, maxSumsEndingAt[index])
    }
    return maxSum
}