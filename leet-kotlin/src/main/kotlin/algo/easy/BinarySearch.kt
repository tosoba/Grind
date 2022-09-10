package algo.easy

private fun search(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size

    while (end - start > 1) {
        val mid = (start + end) / 2
        if (nums[mid] == target) return mid
        else if (nums[mid] > target) end = mid
        else start = mid
    }

    return if (nums[start] == target) start else -1
}