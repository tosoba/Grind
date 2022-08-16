package algo.sliding_window

private fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
    if (nums.isEmpty()) return 0
    var ans = 0
    var left = 0
    var product = 1
    for (right in nums.indices) {
        product *= nums[right]
        while (product >= k && left < right) {
            product /= nums[left++]
        }
        if (product < k) {
            ans += right - left + 1
        }
    }
    return ans
}

fun main() {
    numSubarrayProductLessThanK(intArrayOf(10, 5, 2, 6), 100)
}
