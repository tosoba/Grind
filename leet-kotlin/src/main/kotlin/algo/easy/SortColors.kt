package algo.easy

fun sortColors(nums: IntArray) {
    var zeroes = 0
    var ones = 0
    var twos = 0
    nums.forEach {
        when (it) {
            0 -> ++zeroes
            1 -> ++ones
            2 -> ++twos
        }
    }

    var i = 0

    while (zeroes > 0) {
        nums[i++] = 0
        --zeroes
    }

    while (ones > 0) {
        nums[i++] = 1
        --ones
    }

    while (twos > 0) {
        nums[i++] = 2
        --twos
    }
}