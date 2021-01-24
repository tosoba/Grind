from typing import List


def merge_in_place(arr: List[int], start: int, mid: int, end: int, number_of_inversions: int) -> int:
    i = start
    while i < mid < end:
        if arr[i] > arr[mid]:
            arr[i], arr[mid] = arr[mid], arr[i]
            number_of_inversions += 1
        i += 1
    return number_of_inversions


def merge_sort_in_place_between(arr: List[int], start: int, end: int, number_of_inversions: int = 0) -> int:
    if end - start <= 1:
        return number_of_inversions
    mid = int((end + start) / 2)
    number_of_inversions = merge_sort_in_place_between(arr, 0, mid, number_of_inversions)
    number_of_inversions = merge_sort_in_place_between(arr, mid, end, number_of_inversions)
    return merge_in_place(arr, start, mid, end, number_of_inversions)


def merge_sort_in_place_counting_inversions(arr: List[int]) -> int:
    return 0 if not arr or len(arr) == 1 else merge_sort_in_place_between(arr, 0, len(arr))


if __name__ == '__main__':
    test_arr = [5, 2, 4, 6, 1, 3]
    print(merge_sort_in_place_counting_inversions(test_arr))
    print(test_arr)
