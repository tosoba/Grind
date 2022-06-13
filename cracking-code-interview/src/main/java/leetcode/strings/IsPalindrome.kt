package leetcode.strings

private fun isPalindrome(s: String, l: Int = 0, r: Int = s.length - 1, deletesLimit: Int = 1): Boolean {
    val smod = s.filter { it.isDigit() || it.isLetter() }.lowercase()
    var left = l
    var right = r
    var limit = deletesLimit
    while (left < right) {
        if (smod.elementAt(left) != smod.elementAt(right)) {
            return if (limit > 0) {
                --limit
                isPalindrome(smod.substring(0, left) + smod.substring(left + 1), deletesLimit = limit) ||
                        isPalindrome(smod.substring(0, right) + smod.substring(right + 1), deletesLimit = limit)
            } else {
                false
            }
        }
        right--
        left++
    }
    return true
}

private fun validPalindrome(s: String): Boolean {
    return isPalindrome(s)
}

fun main() {
    println(validPalindrome("abc"))
}