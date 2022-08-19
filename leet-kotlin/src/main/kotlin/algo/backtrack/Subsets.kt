package algo.backtrack

private fun subsets(nums: IntArray): List<List<Int>> {
    nums.sort()

    val subsets = mutableListOf<List<Int>>(emptyList())
    for (index in nums.indices) subsets.add(listOf(index))

    while (subsets.last().size < nums.size) {
        val size = subsets.last().size
        var subsetIndex = subsets.lastIndex
        while (subsets[subsetIndex].size == size) {
            var numIndex = nums.lastIndex
            while (numIndex > subsets[subsetIndex].last()) {
                subsets.add(ArrayList(subsets[subsetIndex]).apply { add(numIndex--) })
            }
            --subsetIndex
        }
    }

    return subsets.map { subset -> subset.map { nums[it] } }.distinct()
}

fun main() {
    println(subsets(intArrayOf(1, 2, 3, 4)))
}