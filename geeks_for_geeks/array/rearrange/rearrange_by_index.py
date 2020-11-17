# https://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/
from typing import List


def rearrange_by_index_starting_from(arr: List[int], start: int = 0) -> int:
    index = start
    carry = arr[arr[index]]
    arr[arr[index]] = index
    index = arr[index]
    for i in range(start + 1, len(arr), 1):
        if arr[carry] == index:
            return i
        tmp = arr[carry]
        arr[carry] = index
        index = carry
        carry = tmp
    return len(arr)


def rearrange_by_index(arr: List[int]):
    start = 0
    while start < len(arr):
        start = rearrange_by_index_starting_from(arr, start)


if __name__ == '__main__':
    test_arr_0 = [1, 3, 0, 2]
    rearrange_by_index(test_arr_0)
    print(test_arr_0)

    test_arr_1 = [2, 0, 1, 4, 5, 3]
    rearrange_by_index(test_arr_1)
    print(test_arr_1)
