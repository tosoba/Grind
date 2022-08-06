package algo.backtrack

private fun letterCasePermutation(s: String): List<String> {
    val perms = mutableListOf<StringBuilder>()
    buildLetterCasePermutations(
        s,
        perms.apply {
            add(StringBuilder(s.first().toString()))
            if (!s.first().isDigit()) {
                add(StringBuilder(toggleCase(s, 0).toString()))
            }
        },
        1
    )
    return perms.map(StringBuilder::toString)
}

private fun buildLetterCasePermutations(s: String, perms: MutableList<StringBuilder>, index: Int) {
    if (index >= s.length) return

    val char = s.elementAt(index)
    if (char.isDigit()) {
        perms.forEach { it.append(char) }
    } else {
        val size = perms.size
        repeat(size) { i ->
            val perm = perms.elementAt(i)
            perms.add(StringBuilder().append(perm).append(toggleCase(s, index)))
            perm.append(char)
        }
    }

    buildLetterCasePermutations(s, perms, index + 1)
}

private fun toggleCase(s: String, index: Int): Char = s.elementAt(index).run {
    if (isLowerCase()) toUpperCase() else toLowerCase()
}

fun main() {
    val ans = letterCasePermutation("a1b2")
}
