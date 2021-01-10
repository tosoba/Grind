from typing import List


def two_elements_with_sum_exist_in(arr: List[int], elements_sum: int) -> bool:
    if not arr or len(arr) == 1:
        return False
    sorted_arr = sorted(arr)
    for i, element in enumerate(sorted_arr):
        searching_for = elements_sum - element
        end = len(sorted_arr)
        start = 0
        mid = int((end + start) / 2)
        while end - start > 1 and arr[mid] != searching_for:
            if searching_for >= sorted_arr[mid]:
                start = mid
            else:
                end = mid
            mid = int((end + start) / 2)
        if mid != i and sorted_arr[mid] == searching_for:
            return True
    return False


if __name__ == '__main__':
    test_arr = [11, 1, 9, 5, 2, 4, 6, 1, 3]
    print(two_elements_with_sum_exist_in(test_arr, 20))
