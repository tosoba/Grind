package leetcode.tree

private class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

private class MostFrequentTreeSum {
    private val sums = mutableMapOf<Int, Int>()

    fun findFrequentTreeSum(root: TreeNode?): IntArray {
        if (root == null) return intArrayOf()
        subtreeSum(root)
        val maxSum = sums.maxOf(Map.Entry<Int, Int>::value)
        return sums.filter { it.value == maxSum }.keys.toIntArray()
    }

    private fun subtreeSum(root: TreeNode?): Int {
        if (root == null) return 0
        if (root.left == null && root.right == null) {
            putSum(root.`val`)
            return root.`val`
        }
        if (root.left == null) {
            val sum = root.`val` + subtreeSum(root.right)
            putSum(sum)
            return sum
        }
        if (root.right == null) {
            val sum = root.`val` + subtreeSum(root.left)
            putSum(sum)
            return sum
        }
        val sum = root.`val` + subtreeSum(root.left) + subtreeSum(root.right)
        putSum(sum)
        return sum
    }

    private fun putSum(sum: Int) {
        sums[sum]?.let {
            sums[sum] = it + 1
        } ?: run {
            sums[sum] = 1
        }
    }
}

fun main() {

}
