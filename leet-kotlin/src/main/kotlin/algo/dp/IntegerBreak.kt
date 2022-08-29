package algo.dp

private data class Product(val value: Int, val parts: List<Int>)

private fun integerBreak(n: Int): Int {
    if (n == 1 || n == 2) return 1

    val memo = mutableListOf(Product(1, listOf(1)), Product(1, listOf(1, 1)))
    for (i in 2 until n) {
        var j = i - 1
        var maxProduct = 0
        var maxProductIndex = 0
        while (j >= 0 && i - j <= 9) {
            val product = memo[j]
            if (product.value * (i - j) > maxProduct) {
                maxProduct = product.value * (i - j)
                maxProductIndex = j
            }
            --j
        }

        val last = memo.last()
        val minLast = last.parts.min()!!
        val prod = last.value / minLast * (minLast + 1)
        if (prod > maxProduct) {
            memo.add(Product(prod, ArrayList(last.parts).apply {
                remove(minLast)
                add(minLast + 1)
            }))
        } else {
            memo.add(Product(maxProduct, ArrayList(memo[maxProductIndex].parts).apply { add(i - maxProductIndex) }))
        }

    }

    return memo.last().value
}

fun main() {
    println(integerBreak(58))
}