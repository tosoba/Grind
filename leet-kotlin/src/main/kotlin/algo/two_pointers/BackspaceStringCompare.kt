package algo.two_pointers

private fun backspaceCompare(s: String, t: String): Boolean {
    var sIndex = s.length - 1
    var tIndex = t.length - 1
    var sBsCount = 0
    var tBsCount = 0

    while (sIndex >= 0 || tIndex >= 0) {
        val sLetter = if (sIndex >= 0) s[sIndex] else ' '
        val tLetter = if (tIndex >= 0) t[tIndex] else ' '

        var sLetterWillRemain = false
        if (sLetter == '#') {
            ++sBsCount
            --sIndex
        } else if (sBsCount > 0) {
            --sBsCount
            --sIndex
        } else {
            sLetterWillRemain = true
        }

        var tLetterWillRemain = false
        if (tLetter == '#') {
            ++tBsCount
            --tIndex
        } else if (tBsCount > 0) {
            --tBsCount
            --tIndex
        } else {
            tLetterWillRemain = true
        }

        if (sLetterWillRemain && tLetterWillRemain) {
            if (sLetter != tLetter) {
                return false
            } else {
                --sIndex
                --tIndex
            }
        }
    }

    return true
}

fun main(args: Array<String>) {
    println(backspaceCompare("y#fo##f", "y#fx#o##f"))
}