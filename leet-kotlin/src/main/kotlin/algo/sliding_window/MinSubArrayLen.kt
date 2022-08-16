package algo.sliding_window

import kotlin.math.min

private fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var ans = Int.MAX_VALUE

    var left = 0
    var sum = 0
    nums.indices.forEach { right ->
        sum += nums[right]
        while (sum - nums[left] >= target && left < right) {
            sum -= nums[left++]
        }
        if (sum >= target) {
            ans = min(ans, right - left + 1)
        }
    }

    return if (ans == Int.MAX_VALUE) 0 else ans
}

fun main() {
    println(
        minSubArrayLen(
            7,
            intArrayOf(2, 3, 1, 2, 4, 3)
        )
    )
    println(
        minSubArrayLen(
            213,
            intArrayOf(
                12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12
            )
        )
    )
}