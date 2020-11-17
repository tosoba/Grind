# https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/

from typing import List


def find_pivot_in(arr: List[int]) -> int:
    return find_pivot(arr, 0, len(arr) - 1)


def find_pivot(arr: List[int], start: int, end: int) -> int:
    mid = int((start + end) / 2)
    if arr[start] <= arr[mid] <= arr[end]:
        return 0

    if mid == start or mid == end:
        assert False

    if arr[mid] > arr[mid + 1]:
        return mid + 1

    if arr[mid] > arr[end]:
        return find_pivot(arr, mid, end)

    if arr[mid] < arr[start]:
        return find_pivot(arr, start, mid)


def shifted_index(index: int, pivot: int, size: int) -> int:
    return (index + pivot) % size


def binary_search_with_pivot(arr: List[int], element: int) -> int:
    return binary_search(arr, element, find_pivot_in(arr), start=0, end=len(arr))


def binary_search(arr: List[int], element: int, pivot: int, start: int, end: int) -> int:
    mid = int((start + end) / 2)
    shifted_mid = shifted_index(mid, pivot, len(arr))
    if arr[shifted_mid] == element:
        return shifted_mid
    elif arr[shifted_mid] > element:
        return binary_search(arr, element, pivot, start=start, end=mid)
    else:
        return binary_search(arr, element, pivot, start=mid, end=end)


if __name__ == '__main__':
    test_arr_0 = [5, 6, 7, 8, 9, 10, 1, 2, 3]
    print(find_pivot_in(test_arr_0))
    print(binary_search_with_pivot(test_arr_0, 10))
