package leetcode.stack

import java.util.*

object DailyTemperatures {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        if (temperatures.isEmpty()) return intArrayOf()
        val tempStack = Stack<Int>()
        val indStack = Stack<Int>()
        val ans = IntArray(temperatures.size) { 0 }
        for (i in temperatures.indices) {
            while (!tempStack.isEmpty() && tempStack.peek() < temperatures[i]) {
                ans[indStack.peek()] = i - indStack.pop()
                tempStack.pop()
            }
            tempStack.push(temperatures[i])
            indStack.push(i)
        }
        return ans
    }
}


fun main() {
    DailyTemperatures.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73))
}