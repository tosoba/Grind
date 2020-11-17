# https://www.geeksforgeeks.org/array-rotation/

from typing import List
from enum import Enum


class Direction(Enum):
    LEFT = 1
    RIGHT = 2


def swap(arr: List[int], index_1: int, index_2: int):
    tmp = arr[index_1]
    arr[index_1] = arr[index_2]
    arr[index_2] = tmp


def rotate(arr: List[int], shift: int, direction: Direction = Direction.LEFT):
    size = len(arr)
    assert 0 <= shift <= size
    if shift == 0 or shift == size:
        return

    if direction is Direction.LEFT:
        def index_shifted_left(start: int) -> int:
            destination = start - shift
            if destination < 0:
                destination = size + destination
            return destination

        for i in range(size - shift):
            swap(arr, i, index_shifted_left(i))
    else:
        def index_shifted_right(start: int) -> int:
            destination = start + shift
            if destination >= size:
                destination = destination - size
            return destination

        for i in range(size - 1, shift - 1, -1):
            swap(arr, i, index_shifted_right(i))


if __name__ == '__main__':
    test_arr_0 = [7,
                  3,
                  100,
                  200,
                  300,
                  350,
                  400,
                  401,
                  402]
    rotate(test_arr_0, 2, Direction.RIGHT)
    print(test_arr_0)
