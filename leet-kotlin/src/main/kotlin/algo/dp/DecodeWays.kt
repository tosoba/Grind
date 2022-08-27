package algo.dp

private fun numDecodings(s: String): Int {
    if (s.isEmpty() || s[0] == '0') return 0
    val n: Int = s.length
    val dp = IntArray(n + 1)
    dp[0] = 1
    dp[1] = if (s[0] != '0') 1 else 0
    for (i in 2..n) {
        val first: Int = Integer.valueOf(s.substring(i - 1, i))
        val second: Int = Integer.valueOf(s.substring(i - 2, i))
        if (first in 1..9) {
            dp[i] += dp[i - 1]
        }
        if (second in 10..26) {
            dp[i] += dp[i - 2]
        }
    }
    return dp[n]
}


fun main() {
//    println(numDecodings("2226"))
    println(numDecodings("111111111111111111111111111111111111111111111"))

}