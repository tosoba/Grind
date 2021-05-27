# https://www.hackerrank.com/challenges/fair-cut/problem
import math
from typing import List, Set


def update_unfairness(current_uf: int, arr: List[int], new_li: int, used_li: Set[int], lu: List[int]) -> int:
    updated_uf = current_uf
    for i in range(0, len(lu)):
        updated_uf += abs(arr[new_li] - arr[lu[i]])
    for li in used_li:
        updated_uf -= abs(arr[li] - arr[new_li])
    return updated_uf


def min_unfairness(current_uf: int, arr: List[int], used_li: Set[int]) -> int:
    min_uf = math.inf
    min_uf_li = 0
    for li in range(0, len(arr)):
        if li in used_li:
            continue
        uf = update_unfairness(current_uf, arr, li, used_li,
                               [lu for lu in range(0, len(arr)) if lu != li and lu not in used_li])
        if uf < min_uf:
            min_uf = uf
            min_uf_li = li
    used_li.add(min_uf_li)
    return min_uf


def fair_cut(arr: List[int], k: int) -> int:
    assert k < len(arr)

    current_uf = 0
    used_li = set()
    for _ in range(1, k + 1):
        current_uf = min_unfairness(current_uf, arr, used_li)

    return current_uf


if __name__ == '__main__':
    print(fair_cut(
        [691259308, 801371251, 345390019, 162749471, 998969126, 308205008, 430442891, 404642721, 532566673, 266540863,
         702197285, 749105392, 775025448, 20453591, 582291534, 132855413, 747557193, 129094259, 474372133, 788391070],
        11))
    print(fair_cut([4, 3, 1, 2], 2))
    pass
