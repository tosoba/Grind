package algo.sliding_window

private fun findAnagrams(s: String, p: String): List<Int> {
    if (s.length < p.length) return emptyList()

    val pChars = mutableMapOf<Char, Int>()
    var zeroCounts = 0

    fun putChar(c: Char) {
        pChars.merge(c, 1) { a, b -> a + b }
    }

    fun putCharIfInP(c: Char) {
        if (pChars.contains(c)) {
            val value = pChars.merge(c, 1) { a, b -> a + b }
            if (value == 1) --zeroCounts
        }
    }

    fun removeChar(c: Char) {
        pChars[c]?.let {
            pChars[c] = it - 1
            if (it - 1 == 0) ++zeroCounts
        }
    }

    p.forEach(::putChar)

    for (i in p.indices) {
        removeChar(s[i])
    }

    val result = mutableListOf<Int>()
    for (i in p.length until s.length) {
        if (zeroCounts == pChars.size) result.add(i - p.length)
        putCharIfInP(s[i - p.length])
        removeChar(s[i])
    }

    if (zeroCounts == pChars.size) {
        result.add(s.length - p.length)
    }
    return result
}

fun main() {
    findAnagrams("cbaebabacd", "abc")
    findAnagrams("abab", "ab")
}