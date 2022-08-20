package algo.backtrack

import kotlin.math.min

private fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    val counts = mutableMapOf<Int, Int>()
    candidates.sorted().forEach { counts.merge(it, 1) { a, b -> a + b } }
    val processedCandidates = mutableListOf<Int>()
    counts.forEach { (candidate, count) ->
        repeat(min(count, target / candidate)) { processedCandidates.add(candidate) }
    }
    val combs = mutableSetOf<MutableList<Int>>()
    buildCombinations(combs, mutableListOf(), 0, 0, processedCandidates.toIntArray(), target)
    return combs.toList()
}

private fun buildCombinations(
    combinations: MutableSet<MutableList<Int>>,
    currentCombination: MutableList<Int>,
    currentSum: Int,
    currentIndex: Int,
    candidates: IntArray,
    target: Int
) {
    if (currentSum > target || currentSum + candidates.asSequence().drop(currentIndex).sum() < target) return
    if (currentSum == target) {
        combinations.add(ArrayList(currentCombination))
        return
    }


    for (i in currentIndex..candidates.lastIndex) {
        currentCombination.add(candidates[i])
        buildCombinations(
            combinations,
            currentCombination,
            currentSum + candidates[i],
            i + 1,
            candidates,
            target
        )
        currentCombination.removeAt(currentCombination.lastIndex)
    }
}

fun main() {
    println(
        combinationSum(
            intArrayOf(
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1
            ), 30
        )
    )
}
