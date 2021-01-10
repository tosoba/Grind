from typing import List


class Incrementer:
    def __init__(self) -> None:
        self._value = 0

    @property
    def value(self) -> int:
        return self._value

    def increment(self):
        self._value += 1


def merge_in_place(arr: List[int], start: int, mid: int, end: int, inc: Incrementer) -> int:
    i = start
    while i < mid < end:
        if arr[i] > arr[mid]:
            arr[i], arr[mid] = arr[mid], arr[i]
            inc.increment()
        i += 1
    return inc.value


def merge_sort_in_place_between(arr: List[int], start: int, end: int, inc: Incrementer = Incrementer()) -> int:
    if end - start <= 1:
        return inc.value
    mid = int((end + start) / 2)
    merge_sort_in_place_between(arr, 0, mid, inc)
    merge_sort_in_place_between(arr, mid, end, inc)
    return merge_in_place(arr, start, mid, end, inc)


def merge_sort_in_place(arr: List[int]) -> int:
    if not arr or len(arr) == 1:
        return 0
    return merge_sort_in_place_between(arr, 0, len(arr))


if __name__ == '__main__':
    test_arr = [5, 2, 4, 6, 1, 3]
    print(merge_sort_in_place(test_arr))
    print(test_arr)
