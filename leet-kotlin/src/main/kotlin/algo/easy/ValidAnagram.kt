package algo.easy

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val counts = mutableMapOf<Char, Int>()
    s.forEach { counts.merge(it, 1) { a, b -> a + b } }

    t.forEach {
        val count = counts[it] ?: return false
        if (count == 1) counts.remove(it)
        else counts[it] = count - 1
    }

    return counts.isEmpty()
}

fun main() {
    println(isAnagram("anagram", "nagaram"))
}