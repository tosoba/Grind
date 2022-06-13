package leetcode.strings

import java.util.*
import kotlin.math.abs

private fun maxAreaBF(height: IntArray): Int {
    var maxArea = area(height, 0, height.size - 1)
    for (i in height.indices) {
        for (j in i until height.size) {
            val area = area(height, i, j)
            if (area > maxArea) maxArea = area
        }
    }
    return maxArea
}

private fun maxAreaNoConstraints(height: IntArray): Int {
    var maxArea = 0
    val indices = TreeMap<Int, MutableSet<Int>>()
    for (i in height.indices) {
        val h = height[i]
        indices[h]?.add(i) ?: run { indices[h] = mutableSetOf(i) }
    }
    for (i in height.indices) {
        val h = height[i]
        val other = indices.descendingKeySet().asSequence().takeWhile { it >= h }.flatMap { indices[it]!! }.maxByOrNull { abs(it - i) }
        if (other != null) {
            val area = area(height, i, other)
            if (area > maxArea) maxArea = area
        }
    }
    return maxArea
}

private fun maxArea(height: IntArray): Int {
    var maxArea = 0
    var left = 0
    var right = height.size - 1
    while (left < right) {
        val hl = height[left]
        val hr = height[right]
        maxArea = Math.max(maxArea, area(height, left, right))
        if (hl > hr) --right
        else ++left
    }
    return maxArea
}

private fun area(height: IntArray, left: Int, right: Int): Int {
    return minOf(height[left], height[right]) * (abs(right - left))
}

fun main() {
    println(maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
}