# https://www.hackerrank.com/challenges/max-array-sum/problem

from typing import List


# no cache - O(2^n)
def max_sum_up_to_index_no_cache(arr: List[int], index: int) -> int:
    if not arr:
        return 0
    assert len(arr) > index >= 0
    if index == 0:
        return max(arr[0], 0)
    if index == 1:
        return max(arr[0], arr[1], 0)
    return max(max_sum_up_to_index_no_cache(arr, index - 1),
               max_sum_up_to_index_no_cache(arr, index - 2) + arr[index])


# with cache - O(n)
def max_sum_up_to_index_with_cache(arr: List[int]) -> int:
    if not arr:
        return 0
    if len(arr) == 1:
        return max(arr[0], 0)
    if len(arr) == 2:
        return max(arr[0], arr[1], 0)

    max_sums_up_to_index: List[int] = [max(arr[0], 0), max(arr[0], arr[1], 0)]
    for i in range(2, len(arr)):
        max_sums_up_to_index.append(max(max_sums_up_to_index[i - 1], max_sums_up_to_index[i - 2] + arr[i]))
    return max_sums_up_to_index[-1]


# for arr = [-2, 1, 3, -4, 5] max non-adjacent sum -> f(n) = max(f(n-1), f(n-2), a[n-1]) where n = len(arr)
def max_non_adjacent_sum(arr: List[int], use_cache: bool = False) -> int:
    if not arr:
        return 0
    elif use_cache:
        return max_sum_up_to_index_with_cache(arr)
    else:
        return max_sum_up_to_index_no_cache(arr, len(arr) - 1)


if __name__ == '__main__':
    test_arr = [-2, 1, 3, -4, 5]
    print(max_non_adjacent_sum(test_arr, True))
