package algo.easy

import kotlin.math.max

private fun maxProfit(prices: IntArray): Int {
    val maxes = IntArray(prices.size) { 0 }
    var maxFromRight = prices.last()
    maxes[maxes.lastIndex] = maxFromRight

    for (index in prices.size - 2 downTo 1) {
        val price = prices[index]
        maxFromRight = max(maxFromRight, price)
        maxes[index] = maxFromRight
    }

    var maxDiff = 0
    for (index in 0 until prices.size - 1) {
        maxDiff = max(maxDiff, maxes[index + 1] - prices[index])
    }
    return maxDiff
}

fun main() {
    maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
}