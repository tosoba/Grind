# https://www.hackerrank.com/challenges/angry-children/problem

import sys

from typing import List


def max_min(k: int, arr: List[int]) -> int:
    arr.sort()
    min_unf: int = sys.maxsize
    for i in range(len(arr) - k + 1):
        delta = arr[i + k - 1] - arr[i]
        if delta < min_unf:
            min_unf = delta
    return min_unf


if __name__ == '__main__':
    test_arr_0 = [7,
                  3,
                  100,
                  200,
                  300,
                  350,
                  400,
                  401,
                  402]
    print(max_min(3, test_arr_0))

    test_arr_1 = [
        10,
        4,
        1,
        2,
        3,
        4,
        10,
        20,
        30,
        40,
        100,
        200
    ]
    print(max_min(4, test_arr_1))
