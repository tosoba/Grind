# https://www.geeksforgeeks.org/find-a-rotation-with-maximum-hamming-distance/

from typing import List


def find_max_hamming_distance(arr: List[int]) -> int:
    max_hamming = 0
    size = len(arr)

    for i in range(1, size, 1):
        current = 0
        for j in range(size):
            if arr[(i + j) % size] != arr[j]:
                current += 1
        if current > max_hamming:
            max_hamming = current

    return max_hamming


if __name__ == '__main__':
    test_arr = [1, 4, 1]
    test_arr_1 = [2, 4, 8, 0]
    print(find_max_hamming_distance(test_arr_1))
