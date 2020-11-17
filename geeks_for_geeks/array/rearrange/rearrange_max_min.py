# TODO: https://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form-set-2-o1-extra-space/
from typing import List


def destination_index_of(index: int, size: int) -> int:
    return 2 * index + 1 if index < size // 2 else 2 * (size - 1 - index)


def coalesce_to_size(index: int, size: int) -> int:
    return index % size if index > 0 else size + index

# TODO: check if arranged method

def rearrange_max_min(arr: List[int]):
    if not arr:
        return
    size = len(arr)
    elements_to_shift = [(arr[0], 0)]
    swaps = 0
    while True:
        to_shift = elements_to_shift.pop(0)
        destination = destination_index_of(to_shift[1], size)
        if arr[destination] == to_shift[0]:
            if swaps == size:
                break
            else:
                index_to_shift = coalesce_to_size(destination + 1, size)
                elements_to_shift.append((arr[index_to_shift], index_to_shift))
        else:
            elements_to_shift.append((arr[destination], destination))
            arr[destination] = to_shift[0]
            swaps += 1


if __name__ == '__main__':
    test_arr_0 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
    rearrange_max_min(test_arr_0)
    print(test_arr_0)

    test_arr_1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]
    rearrange_max_min(test_arr_1)
    print(test_arr_1)
