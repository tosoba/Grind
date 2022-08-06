package algo.dp

private fun rob(nums: IntArray): Int {
    if (nums.size == 1) return nums[0]
    if (nums.size == 2) return Integer.max(nums[0], nums[1])

    val money = intArrayOf(nums[0], nums[1], nums[2] + nums[0])
    for (i in 3 until nums.size) {
        money[i % 3] = nums[i] + Integer.max(money[(i - 2) % 3], money[(i - 3) % 3])
    }
    return money.max()!!
}

fun main() {
    val ans = rob(intArrayOf(1, 2, 3, 1))
}