# https://www.geeksforgeeks.org/minimum-swaps-required-bring-elements-less-equal-k-together/
from typing import List


def min_swaps_to_bunch_all_less_than(k: int, arr: List[int]) -> int:
    max_bunch_size = 0
    current_bunch_size = 0
    elements_less_than_k = 0
    for i in range(len(arr)):
        if arr[i] <= k:
            elements_less_than_k += 1
            current_bunch_size += 1
        else:
            if current_bunch_size > max_bunch_size:
                max_bunch_size = current_bunch_size
            current_bunch_size = 0
    return elements_less_than_k - max_bunch_size


if __name__ == '__main__':
    test_arr_0 = [2, 1, 5, 6, 3]
    test_arr_1 = [2, 7, 9, 5, 8, 7, 4]
    print(min_swaps_to_bunch_all_less_than(3, test_arr_0))
    print(min_swaps_to_bunch_all_less_than(5, test_arr_1))
