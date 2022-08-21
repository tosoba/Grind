package algo.backtrack

import kotlin.math.max

private fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList()
    val combinations = mutableListOf<String>()
    buildCombinations(digits, 0, StringBuilder(), combinations)
    return combinations
}

private fun buildCombinations(digits: String, index: Int, current: StringBuilder, combinations: MutableList<String>) {
    if (index == digits.length) {
        combinations.add(current.toString())
        return
    }

    when (digits[index]) {
        '2' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'a')
            current.buildAndBacktrack(digits, index + 1, combinations, 'b')
            current.buildAndBacktrack(digits, index + 1, combinations, 'c')
        }
        '3' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'd')
            current.buildAndBacktrack(digits, index + 1, combinations, 'e')
            current.buildAndBacktrack(digits, index + 1, combinations, 'f')
        }
        '4' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'g')
            current.buildAndBacktrack(digits, index + 1, combinations, 'h')
            current.buildAndBacktrack(digits, index + 1, combinations, 'i')
        }
        '5' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'j')
            current.buildAndBacktrack(digits, index + 1, combinations, 'k')
            current.buildAndBacktrack(digits, index + 1, combinations, 'l')
        }
        '6' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'm')
            current.buildAndBacktrack(digits, index + 1, combinations, 'n')
            current.buildAndBacktrack(digits, index + 1, combinations, 'o')
        }
        '7' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'p')
            current.buildAndBacktrack(digits, index + 1, combinations, 'q')
            current.buildAndBacktrack(digits, index + 1, combinations, 'r')
            current.buildAndBacktrack(digits, index + 1, combinations, 's')
        }
        '8' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 't')
            current.buildAndBacktrack(digits, index + 1, combinations, 'u')
            current.buildAndBacktrack(digits, index + 1, combinations, 'v')
        }
        '9' -> {
            current.buildAndBacktrack(digits, index + 1, combinations, 'w')
            current.buildAndBacktrack(digits, index + 1, combinations, 'x')
            current.buildAndBacktrack(digits, index + 1, combinations, 'y')
            current.buildAndBacktrack(digits, index + 1, combinations, 'z')
        }
    }
}

private fun StringBuilder.buildAndBacktrack(digits: String, index: Int, combinations: MutableList<String>, char: Char) {
    append(char)
    buildCombinations(digits, index, this, combinations)
    setLength(max(0, length - 1))
}

fun main() {
    println(letterCombinations("23"))
}