# https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
from typing import List


def rearrange(arr: List[int]):
    swap_to_index = 0
    size = len(arr)

    # move all negatives to front of the list
    for i in range(size):
        if arr[i] < 0:
            arr[i], arr[swap_to_index] = arr[swap_to_index], arr[i]
            swap_to_index += 1

    swap_to_index += 1

    # place all positives at odd indices because why not
    for i in range(1, size, 2):
        if arr[i] > 0 or swap_to_index >= size:
            break
        arr[i], arr[swap_to_index] = arr[swap_to_index], arr[i]
        swap_to_index += 2


if __name__ == '__main__':
    test_arr_0 = [-1, 2, -3, 4, 5, 6, -7, 8, 9, -10, 2, -23]
    rearrange(test_arr_0)
    print(test_arr_0)
