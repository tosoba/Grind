package algo.graph

import java.util.*

private fun accountsMerge(accounts: List<List<String>>): List<List<String>> {
    val accountsMap = mutableMapOf<String, MutableList<TreeSet<String>>>()
    accounts.forEach { account ->
        val name = account[0]
        accountsMap[name]?.add(TreeSet(account.drop(1))) ?: run {
            accountsMap[name] = mutableListOf(TreeSet(account.drop(1)))
        }
    }

    val ans = mutableListOf<List<String>>()

    accountsMap.forEach { (name, sets) ->
        val roots = IntArray(sets.size) { it }

        fun rootOf(index: Int): Int =
            if (roots[index] == index) index
            else rootOf(roots[index])

        for (i in sets.indices) {
            for (j in 0 until i) {
                val rootI = rootOf(i)
                val rootJ = rootOf(j)
                if (rootI != rootJ && !Collections.disjoint(sets[i], sets[j])) {
                    roots[rootI] = rootJ
                }
            }
        }

        val result = mutableMapOf<Int, TreeSet<String>>()
        roots.forEachIndexed { index, subRoot ->
            val root = rootOf(subRoot)
            result[root]?.addAll(sets[index]) ?: run {
                result[root] = TreeSet(sets[index])
            }
        }

        result.values.forEach {
            ans.add(mutableListOf(name) + it)
        }
    }

    return ans
}

fun main() {
    accountsMerge(
        listOf(
            listOf("John", "johnsmith@mail.com", "john_newyork@mail.com"),
            listOf("John", "johnsmith@mail.com", "john00@mail.com"),
            listOf("Mary", "mary@mail.com"),
            listOf("John", "johnnybravo@mail.com")
        )
    )

    accountsMerge(
        listOf(
            listOf("David", "David0@m.co", "David1@m.co"),
            listOf("David", "David3@m.co", "David4@m.co"),
            listOf("David", "David4@m.co", "David5@m.co"),
            listOf("David", "David2@m.co", "David3@m.co"),
            listOf("David", "David1@m.co", "David2@m.co")
        )
    )
}