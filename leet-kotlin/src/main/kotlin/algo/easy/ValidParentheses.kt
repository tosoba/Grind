package algo.easy

import java.util.*

fun isValid(s: String): Boolean {
    val lefts = Stack<Char>()
    s.forEach {
        if (it == '(' || it == '[' || it == '{') lefts.push(it)
        else if (lefts.isEmpty()) return false
        else {
            val left = lefts.pop()
            if ((it == ')' && left != '(') || (it == ']' && left != '[') || (it == '}' && left != '{')) return false
        }
    }
    return lefts.isEmpty()
}

fun main() {
    println(isValid("()"))
}