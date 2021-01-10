from typing import List


def selection_sort(arr: List[int]):
    for i in range(0, len(arr)):
        min_index = i
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index], arr[i]


if __name__ == '__main__':
    test_arr = [5, 2, 4, 6, 1, 3]
    selection_sort(test_arr)
    print(test_arr)
