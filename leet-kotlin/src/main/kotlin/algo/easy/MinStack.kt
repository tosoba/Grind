package algo.easy

import java.util.*

class MinStack {
    private val st = Stack<Int>()
    private val mins = Stack<Int>()

    fun push(`val`: Int) {
        st.push(`val`)
        if (mins.isEmpty() || mins.peek() >= `val`) mins.push(`val`)
    }

    fun pop() {
        val popped = st.pop()
        if (popped == mins.peek()) mins.pop()
    }

    fun top(): Int = st.peek()

    fun getMin(): Int = mins.peek()
}