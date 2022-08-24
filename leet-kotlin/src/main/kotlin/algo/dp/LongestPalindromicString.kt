package algo.dp

import kotlin.math.min

private fun longestPalindrome(s: String): String {
    if (s.length == 1) return s

    var start = 0
    var end = 1

    for (i in s.indices) {
        var j = 0
        while (i - j - 1 >= 0 && i + j + 1 < s.length && s[i - j - 1] == s[i + j + 1]) {
            j++
        }
        if (2 * j + 1 > end - start) {
            start = i - j
            end = min(i + j + 1, s.length)
        }
    }

    for (i in 0 until s.length - 1) {
        if (s[i] != s[i + 1]) continue

        var j = 0
        while (i - j - 1 >= 0 && i + j + 2 < s.length && s[i - j - 1] == s[i + j + 2]) {
            j++
        }
        if (2 * j + 2 > end - start) {
            start = i - j
            end = min(i + j + 2, s.length)
        }
    }

    return s.substring(start, end)
}