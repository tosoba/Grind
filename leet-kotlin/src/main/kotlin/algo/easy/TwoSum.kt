package algo.easy

private fun twoSum(nums: IntArray, target: Int): IntArray {
    val indices = mutableMapOf<Int, MutableList<Int>>()
    nums.forEachIndexed { index, num ->
        indices[num]?.let { if (it.size > 1) return@forEachIndexed else it.add(index) }
            ?: run { indices[num] = mutableListOf(index) }
    }

    nums.forEachIndexed { index, num ->
        val searchFor = target - num
        val searchForIndices = indices[searchFor] ?: return@forEachIndexed
        if ((searchFor != num && searchForIndices.isNotEmpty()) || (searchFor == num && searchForIndices.size > 1)) {
            return intArrayOf(index, searchForIndices.first { it != index })
        }
    }

    throw IllegalArgumentException()
}