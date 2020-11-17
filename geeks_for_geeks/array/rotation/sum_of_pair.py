# https://www.geeksforgeeks.org/given-a-sorted-and-rotated-array-find-if-there-is-a-pair-with-a-given-sum/

from typing import List
from geeks_for_geeks.array.rotation.rotated_binary_search import shifted_index, find_pivot_in


def pair_with_sum_exists(s: int, arr: List[int]) -> bool:
    pivot = find_pivot_in(arr)
    left = 0
    size = len(arr)
    right = size - 1
    while left != right:
        current_sum = arr[shifted_index(left, pivot, size)] + arr[shifted_index(right, pivot, size)]
        if current_sum == s:
            return True
        elif current_sum > s:
            right -= 1
        else:
            left += 1
    return False


if __name__ == '__main__':
    test_arr_0 = [5, 6, 7, 8, 9, 10, 1, 2, 3]
    print(pair_with_sum_exists(3, test_arr_0))
