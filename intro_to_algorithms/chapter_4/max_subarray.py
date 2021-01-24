from typing import List


def max_sub_array_of(arr: List[int]) -> (int, int):
    if not arr or len(arr) < 2:
        return None

    left = 0
    right = 1
    current_left = 1 if arr[left] > arr[right] else 0
    current_max_delta = arr[right] - arr[left]
    for i, element in enumerate(arr[2:], start=2):
        if arr[i] > arr[right]:
            right = i
        if element < arr[i - 1]:
            delta = arr[i - 1] - arr[current_left]
            if delta > current_max_delta:
                current_max_delta = delta
                left = current_left
                right = i - 1
            current_left = i
    return left, right


if __name__ == '__main__':
    test_arr = [100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97]
    l, r = max_sub_array_of(test_arr)
    print(l, r)
    print(test_arr[r] - test_arr[l])
    pass
