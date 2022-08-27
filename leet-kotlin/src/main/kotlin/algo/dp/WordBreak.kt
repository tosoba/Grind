package algo.dp

private fun wordBreak(s: String, wordDict: List<String>): Boolean =
    canBeSegmented(s, 0, wordDict.toSet(), mutableSetOf())

private fun canBeSegmented(
    s: String,
    start: Int,
    words: Set<String>,
    checked: MutableSet<Int>
): Boolean {
    if (start == s.length) return true
    var current = ""
    for (i in start until s.length) {
        val letter = s[i]
        current += letter
        if (words.contains(current) && !checked.contains(i)) {
            if (canBeSegmented(s, i + 1, words, checked)) {
                return true
            } else {
                checked.add(i)
            }
        }
    }

    return words.contains(current)
}

fun main() {
    println(wordBreak("leetcode", listOf("leet", "code")))
    println(wordBreak("applepenapple", listOf("apple", "pen")))
    println(wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat")))

}