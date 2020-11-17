# https://www.geeksforgeeks.org/move-zeroes-end-array/
from typing import List


def move_zeroes_to_end(arr: List[int]):
    swap_to_index = len(arr) - 1
    for i in range(len(arr) - 1, -1, -1):
        if arr[i] == 0:
            arr[i], arr[swap_to_index] = arr[swap_to_index], arr[i]
            swap_to_index -= 1


if __name__ == '__main__':
    test_arr_0 = [1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0]
    move_zeroes_to_end(test_arr_0)
    print(test_arr_0)
