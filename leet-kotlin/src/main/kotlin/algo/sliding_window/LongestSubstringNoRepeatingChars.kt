package algo.sliding_window

private fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty() || s.length == 1) return s.length

    var maxLen = 1
    val chars = mutableSetOf(s.first())
    var start = 0
    var end = 1
    while (end < s.length) {
        while (chars.contains(s.elementAt(end)) && start <= end) {
            chars.remove(s.elementAt(start++))
        }
        chars.add(s.elementAt(end++))
        maxLen = Integer.max(maxLen, chars.size)
    }
    return maxLen
}