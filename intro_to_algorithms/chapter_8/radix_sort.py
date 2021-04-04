import math
from typing import List, Dict


def counting_sort(arr: List[int], divisor: int) -> List[int]:
    if not arr:
        return arr

    counts: Dict[int, int] = {}
    max_element = -math.inf
    min_element = math.inf
    for element in arr:
        divided = element // divisor
        counts.setdefault(divided, 0)
        counts[divided] += 1
        if divided > max_element:
            max_element = divided
        if divided < min_element:
            min_element = divided

    value = min_element + 1
    previous_value_count = counts[min_element]
    while value <= max_element:
        if value in counts.keys():
            counts[value] += previous_value_count
            previous_value_count = counts[value]
        value += 1

    sorted_arr: List[int] = [0] * len(arr)
    for i in range(len(arr) - 1, -1, -1):
        divided = arr[i] // divisor
        destination_index = counts[divided] - 1
        sorted_arr[destination_index] = arr[i]
        counts[divided] -= 1
    return sorted_arr


def radix_sort(arr: List[int]) -> List[int]:
    if not arr:
        return arr

    max_element = -math.inf
    for element in arr:
        if element > max_element:
            max_element = element

    digits = 0
    while max_element != 0:
        max_element //= 10
        digits += 1

    sorted_arr = arr
    for digit in range(0, digits):
        sorted_arr = counting_sort(sorted_arr, pow(10, digit))  # sorting subroutine must be stable!
    return sorted_arr


if __name__ == '__main__':
    test_arr = [222, 318, 537, 131, 443, 675, 936, 444, 325, 22, 7]
    print(radix_sort(test_arr))
