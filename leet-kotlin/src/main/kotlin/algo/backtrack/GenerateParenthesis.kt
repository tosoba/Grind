package algo.backtrack

import kotlin.math.max

fun generateParenthesis(n: Int): List<String> {
    val answer = mutableListOf<String>()
    buildAnswer(n, n, answer, StringBuilder())
    return answer
}

private fun buildAnswer(leftCount: Int, rightCount: Int, answer: MutableList<String>, current: StringBuilder) {
    if (leftCount == 0 && rightCount == 0) {
        answer.add(current.toString())
        return
    }

    if (leftCount > 0) {
        current.append('(')
        buildAnswer(leftCount - 1, rightCount, answer, current)
        current.setLength(max(0, current.length - 1))
    }

    if (leftCount < rightCount) {
        current.append(')')
        buildAnswer(leftCount, rightCount - 1, answer, current)
        current.setLength(max(0, current.length - 1))
    }
}

fun main() {
    println(generateParenthesis(8))
}