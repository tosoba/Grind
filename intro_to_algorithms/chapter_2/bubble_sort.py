from typing import List


def bubble_sort(arr: List[int]):
    for i in range(0, len(arr)):
        for j in range(len(arr) - 1, i, -1):
            if arr[j] < arr[j - 1]:
                arr[j], arr[j - 1] = arr[j - 1], arr[j]


if __name__ == '__main__':
    test_arr = [11, 1, 9, 5, 2, 4, 6, 1, 3]
    bubble_sort(test_arr)
    print(test_arr)
