from typing import List


# linear
def max_sub_array_window_of(arr: List[int]) -> (int, int, int):
    if not arr or len(arr) < 2:
        return None

    left = 0
    right = 1
    current_left = right if arr[left] > arr[right] else left
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
    return left, right, arr[right] - arr[left]


# divide and conquer - O(nlg(n))
def max_sub_array_dnc_of(arr: List[int]) -> (int, int, int):
    def max_sub_array_crossing_mid(left: int, right: int) -> (int, int, int):
        mid = (right + left) // 2
        min_left = arr[left]
        min_left_index = left
        for i in range(left + 1, mid + 1):
            if arr[i] < min_left:
                min_left = arr[i]
                min_left_index = i
        max_right = arr[mid]
        max_right_index = mid
        for i in range(mid, right + 1):
            if arr[i] > max_right:
                max_right = arr[i]
                max_right_index = i
        return min_left_index, max_right_index, arr[max_right_index] - arr[min_left_index]

    def max_sub_array_dnc_between(left: int, right: int) -> (int, int, int):
        if abs(left - right) == 2:
            return left, right, arr[right] - arr[left]
        mid = (right + left) // 2
        return max([max_sub_array_dnc_between(left, mid),
                    max_sub_array_dnc_between(mid, right),
                    max_sub_array_crossing_mid(left, right)], key=lambda x: x[-1])

    return None if not arr or len(arr) < 2 else max_sub_array_dnc_between(0, len(arr) - 1)


if __name__ == '__main__':
    test_arr = [100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97]
    l, r, delta = max_sub_array_dnc_of(test_arr)
    print(l, r, delta)
    pass
