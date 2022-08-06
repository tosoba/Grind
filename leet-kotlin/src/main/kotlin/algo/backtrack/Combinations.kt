package algo.backtrack

private fun combine(n: Int, k: Int): List<List<Int>> {
    val comb = MutableList(k) { it + 1 }
    val combs = mutableListOf<List<Int>>(ArrayList(comb))

    fun canBeIncremented(): Boolean {
        var target = n
        for (i in k - 1 downTo 0) {
            if (comb.elementAt(i) != target--) return true
        }
        return false
    }

    fun incrementComb() {
        var maxValue = n
        var incrementedIndex = -1
        for (i in k - 1 downTo 0) {
            if (comb.elementAt(i) != maxValue--) {
                incrementedIndex = i
                break
            }
        }

        comb[incrementedIndex] += 1
        var nextValue = comb[incrementedIndex]
        for (i in incrementedIndex + 1 until k) {
            comb[i] = ++nextValue
        }
    }

    while (canBeIncremented()) {
        incrementComb()
        combs.add(ArrayList(comb))
    }

    return combs
}

fun main() {
    val ans = combine(4, 2)
    println(ans)
}