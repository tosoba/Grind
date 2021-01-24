from typing import List


def merge(arr1: List[int], arr2: List[int]) -> List[int]:
    i1 = i2 = 0
    result: List[int] = []
    while i1 < len(arr1) and i2 < len(arr2):
        if arr1[i1] <= arr2[i2]:
            result.append(arr1[i1])
            i1 += 1
        else:
            result.append(arr2[i2])
            i2 += 1
    if i1 < len(arr1):
        result += arr1[i1:]
    elif i2 < len(arr2):
        result += arr2[i2:]
    return result


def merge_sort(arr: List[int]) -> List[int]:
    if not arr or len(arr) == 1:
        return arr
    mid = int(len(arr) / 2)
    return merge(merge_sort(arr[:mid]), merge_sort(arr[mid:]))


def merge_in_place(arr: List[int], start: int, mid: int, end: int):
    i = start
    while i < mid < end:
        if arr[i] > arr[mid]:
            arr[i], arr[mid] = arr[mid], arr[i]
        i += 1


def merge_sort_in_place_between(arr: List[int], start: int, end: int):
    if end - start <= 1:
        return
    mid = int((end + start) / 2)
    merge_sort_in_place_between(arr, 0, mid)
    merge_sort_in_place_between(arr, mid, end)
    merge_in_place(arr, start, mid, end)


def merge_sort_in_place(arr: List[int]):
    if not arr or len(arr) == 1:
        return
    merge_sort_in_place_between(arr, 0, len(arr))


if __name__ == '__main__':
    test_arr = [2, 3, 5, 5, 99, 45, 76, 8, 6, 1, 4]
    merge_sort_in_place(test_arr)
    print(test_arr)
