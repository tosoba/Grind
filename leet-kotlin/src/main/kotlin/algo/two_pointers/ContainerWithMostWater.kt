package algo.two_pointers

import kotlin.math.max
import kotlin.math.min

private fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var maxArea = 0
    while (left < right) {
        maxArea = max(maxArea, area(height, left, right))
        if (height[left] > height[right]) {
            --right
        } else if (height[right] > height[left]) {
            ++left
        } else {
            --right
            ++left
        }
    }
    return maxArea
}

private fun area(height: IntArray, left: Int, right: Int): Int {
    return min(height[left], height[right]) * (right - left)
}