package algo.dp

import java.util.*

private fun canJump(nums: IntArray): Boolean {
    if (nums[0] >= nums.size - 1) return true

    val q = ArrayDeque<Int>().apply { addLast(0) }
    val visited = BooleanArray(nums.size) { false }

    fun addIndices(index: Int): Boolean {
        var length = nums[index]
        while (length > 0) {
            if (index + length >= nums.lastIndex) return true
            if (!visited[index + length]) {
                q.addLast(index + length--)
            } else {
                --length
            }
        }
        return false
    }

    while (q.isNotEmpty()) {
        val index = q.pollFirst()
        if (visited[index]) continue
        visited[index] = true
        if (addIndices(index)) return true
    }

    return visited.last()
}

fun main() {
    println(canJump(intArrayOf(3, 2, 1, 0, 4)))
}