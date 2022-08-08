package algo.graph

import java.util.*

fun canFinishTopSort(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val adj = Array(numCourses) { mutableSetOf<Int>() }
    val degrees = mutableMapOf<Int, Int>()

    prerequisites.forEach { (a, b) ->
        adj[a].add(b)
        degrees[b]?.let { degrees[b] = it + 1 } ?: run { degrees[b] = 1 }
    }

    val availableCourses = ArrayDeque<Int>()
    repeat(numCourses) { course ->
        if (!degrees.containsKey(course)) availableCourses.addLast(course)
    }

    var coursesLeft = numCourses
    while (availableCourses.isNotEmpty()) {
        val course = availableCourses.pollFirst()
        --coursesLeft
        adj[course].forEach { nextCourse ->
            degrees[nextCourse] = degrees[nextCourse]!! - 1
            if (degrees[nextCourse] == 0) {
                degrees.remove(nextCourse)
                availableCourses.addLast(nextCourse)
            }
        }
    }

    return coursesLeft == 0
}

fun main() {
    canFinishTopSort(
        20,
        arrayOf(
            intArrayOf(0, 10),
            intArrayOf(3, 18),
            intArrayOf(5, 5),
            intArrayOf(6, 11),
            intArrayOf(11, 14),
            intArrayOf(13, 1),
            intArrayOf(15, 1),
            intArrayOf(17, 4)
        )
    )
}