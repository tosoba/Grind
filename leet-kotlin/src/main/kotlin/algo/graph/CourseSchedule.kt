package algo.graph

import java.util.*

fun canFinishTopSort(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val adj = Array(numCourses) { mutableSetOf<Int>() }
    val degrees = Array(numCourses) { 0 }

    prerequisites.forEach { (a, b) ->
        adj[a].add(b)
        ++degrees[b]
    }

    val availableCourses = ArrayDeque<Int>()
    degrees.forEachIndexed { course, degree ->
        if (degree == 0) {
            availableCourses.addLast(course)
            degrees[course] = -1
        }
    }

    var coursesLeft = numCourses
    while (availableCourses.isNotEmpty()) {
        val course = availableCourses.pollFirst()
        degrees[course] = -1
        --coursesLeft
        adj[course].forEach { nextCourse ->
            --degrees[nextCourse]
            if (degrees[nextCourse] == 0) {
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