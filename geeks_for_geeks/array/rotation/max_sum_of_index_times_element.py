# https://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/

from typing import List


def max_sum_of_elements_times_index(arr: List[int]) -> int:
    sum_of_elements = 0
    max_sum_times_index = 0
    size = len(arr)

    for i in range(size):
        sum_of_elements += arr[i]
        max_sum_times_index += arr[i] * i

    current = max_sum_times_index
    for i in range(size - 1, 0, -1):
        current = current + sum_of_elements - arr[i] * size
        if current > max_sum_times_index:
            max_sum_times_index = current

    return max_sum_times_index


if __name__ == '__main__':
    test_arr_0 = [1, 20, 2, 10]
    test_arr_1 = [10, 1, 2, 3, 4, 5, 6, 7, 8, 9]

    print(max_sum_of_elements_times_index(test_arr_1))
