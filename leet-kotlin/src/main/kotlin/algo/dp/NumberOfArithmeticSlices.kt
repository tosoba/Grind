package algo.dp

private fun numberOfArithmeticSlices(nums: IntArray): Int {
    if (nums.size < 3) return 0

    var ans = 0
    var i = 0
    while (i < nums.size - 1) {
        val diff = nums[i + 1] - nums[i]
        var j = 0
        var inc = 1
        while (i + j + 2 < nums.size && nums[i + j + 2] - nums[i + j + 1] == diff) {
            ++j
            ans += inc++
        }

        i += j + 1
    }

    return ans
}