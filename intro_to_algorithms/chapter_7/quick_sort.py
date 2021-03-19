from typing import List


def partition_between(arr: List[int], left: int, right: int) -> int:
    if not arr or right < 0 or right >= len(arr) or left < 0 or left >= len(arr) or left >= right:
        return -1

    pivot_index = right
    i = left
    while i != pivot_index:
        if arr[i] > arr[pivot_index]:
            arr[i], arr[pivot_index - 1] = arr[pivot_index - 1], arr[i]
            arr[pivot_index], arr[pivot_index - 1] = arr[pivot_index - 1], arr[pivot_index]
            pivot_index -= 1
        else:
            i += 1
    return pivot_index


def quick_sort(arr: List[int]):
    def quick_sort_between(left: int, right: int):
        pivot_index = partition_between(arr, left, right)
        if pivot_index == -1:
            return
        quick_sort_between(left, pivot_index - 1)
        quick_sort_between(pivot_index + 1, right)

    quick_sort_between(0, len(arr) - 1)


if __name__ == '__main__':
    test_arr = [2, 8, 7, 1, 3, 5, 6, 4]
    quick_sort(test_arr)
    print(test_arr)
    pass
