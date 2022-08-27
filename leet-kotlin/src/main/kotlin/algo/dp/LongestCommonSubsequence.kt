package algo.dp

import kotlin.math.max

fun longestCommonSubsequence(text1: String, text2: String): Int {
    if (text1.isEmpty() || text2.isEmpty()) return 0

    val memo = Array(text1.length + 1) { IntArray(text2.length + 1) }
    for (i in text1.indices) {
        for (j in text2.indices) {
            memo[i + 1][j + 1] =
                if (text1[i] == text2[j]) memo[i][j] + 1
                else max(memo[i + 1][j], memo[i][j + 1])
        }
    }

    return memo[text1.length][text2.length]
}


fun main() {
    println(longestCommonSubsequence("ezupkr", "ubmrapg"))
}