package algo.backtrack

private fun permuteUnique(nums: IntArray): List<List<Int>> {
    val perms = nums.indices.map { mutableSetOf(it) }.toMutableList()
    buildPerms(perms, nums)
    return perms.map { perm -> perm.map { nums[it] } }.distinct()
}

private tailrec fun buildPerms(perms: MutableList<MutableSet<Int>>, nums: IntArray) {
    if (perms.all { it.size == nums.size }) return

    val len = perms.size
    for (i in 0 until len) {
        val perm = perms[i]
        val copy = LinkedHashSet(perm)
        var first = true
        for (index in nums.indices) {
            if (copy.contains(index)) continue
            if (first) {
                perm.add(index)
                first = false
            } else {
                perms.add(LinkedHashSet(copy).apply { add(index) })
            }
        }
    }

    buildPerms(perms, nums)
}

fun main() {
    println(permuteUnique(intArrayOf(1, 1, 2)))
}