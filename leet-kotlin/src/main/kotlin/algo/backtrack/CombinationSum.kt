package algo.backtrack

private fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
    candidates.sort()
    val combs = candidates.filter { it <= target }.map { Combination(mutableListOf(it), it) }.toMutableSet()
    return buildCombinations(combs, candidates, target).map(Combination::parts)
}

// solution without backtracking (unless you count removeIf line as backtracking :P) - it's kind of slow due to the need of copying combinations
// MORE EFFICIENT solution in CombinationSumUnique
private fun buildCombinations(
    combinations: MutableSet<Combination>,
    candidates: IntArray,
    target: Int
): MutableSet<Combination> {
    if (combinations.all { it.sum == target }) return combinations

    val newCombinations = mutableSetOf<Combination>()
    val iterator = combinations.iterator()
    while (iterator.hasNext()) {
        val comb = iterator.next()
        val currentSum = comb.sum
        if (currentSum == target) {
            newCombinations.add(comb)
            continue
        }

        var j = 0
        while (j < candidates.size && candidates[j] + currentSum <= target) {
            newCombinations.add(
                Combination(
                    ArrayList(comb.parts).apply {
                        add(candidates[j])
                        sort()
                    },
                    currentSum + candidates[j++]
                )
            )
        }
    }

    newCombinations.removeIf { it.sum != target && it.sum + candidates[0] > target }

    return buildCombinations(newCombinations, candidates, target)
}

private data class Combination(val parts: MutableList<Int>, var sum: Int)

fun main() {
    println(combinationSum(intArrayOf(2, 3, 6, 7), 7))
}
