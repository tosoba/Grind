import math
from typing import List, Dict


def counting_sort(arr: List[int]) -> List[int]:
    if not arr:
        return arr

    counts: Dict[int, int] = {}
    max_element = -math.inf
    min_element = math.inf
    for element in arr:
        counts.setdefault(element, 0)
        counts[element] += 1
        if element > max_element:
            max_element = element
        if element < min_element:
            min_element = element

    value = min_element + 1
    previous_value_count = counts[min_element]
    while value <= max_element:
        if value in counts.keys():
            counts[value] += previous_value_count
            previous_value_count = counts[value]
        value += 1

    # after while loop counts[value] = number of values <= value

    sorted_arr: List[int] = [0] * len(arr)
    for i in range(len(arr) - 1, -1, -1):  # from 0 to len(arr) works as well but not stable
        destination_index = counts[arr[i]] - 1
        sorted_arr[destination_index] = arr[i]
        counts[arr[i]] -= 1
    return sorted_arr


if __name__ == '__main__':
    test_arr = [2, 8, 7, 1, 3, 5, 6, 4, 5]
    print(counting_sort(test_arr))
