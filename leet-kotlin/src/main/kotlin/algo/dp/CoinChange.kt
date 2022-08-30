package algo.dp

import kotlin.math.min

private fun coinChange(coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0
    coins.sort()
    val checked = mutableSetOf<Int>()
    return coinChange(coins, amount, 0, 0, coins.lastIndex, checked, Int.MAX_VALUE)
}

private fun coinChange(
    coins: IntArray,
    amount: Int,
    currentAmount: Int,
    numberOfCoins: Int,
    coinIndex: Int,
    checked: MutableSet<Int>,
    minResult: Int
): Int {
    if (currentAmount == amount) return numberOfCoins
    if (checked.contains(currentAmount) || coinIndex < 0) return -1
    if (numberOfCoins >= minResult || coins[0] + currentAmount > amount) {
        if (coins[0] + currentAmount > amount) {
            checked.add(currentAmount)
        }
        return if (minResult == Int.MAX_VALUE) -1 else minResult
    }

    var index = coinIndex
    while (index >= 0 && coins[index] + currentAmount > amount) --index

    var currentResult = minResult
    while (index >= 0) {
        if (checked.contains(currentAmount + coins[index])) {
            --index
            continue
        }

        val result =
            coinChange(coins, amount, currentAmount + coins[index], numberOfCoins + 1, index, checked, currentResult)
        if (result > -1) {
            currentResult = min(result, currentResult)
        } else {
            checked.add(currentAmount + coins[index])
        }
        --index
    }

    if (currentResult >= minResult && currentResult != Int.MAX_VALUE) checked.add(currentAmount)
    return if (currentResult == Int.MAX_VALUE) -1 else currentResult
}

fun main() {
//    println(coinChange(intArrayOf(186, 419, 83, 408), 6249))
    //[186,419,83,408]
    //6249
//    println(coinChange(intArrayOf(3, 7, 405, 436), 8839))
    //[3,7,405,436]
    //8839
    println(coinChange(intArrayOf(125, 146, 125, 252, 226, 25, 24, 308, 50), 8402))
    //[125,146,125,252,226,25,24,308,50]
    //8402

    //[156,265,40,280]
    //9109
//    println(coinChange(intArrayOf(156,265,40,280), 9109))

}