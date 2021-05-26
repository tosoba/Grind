# https://www.hackerrank.com/challenges/fair-cut/problem
import math
from typing import List, Set


def unfairness(arr: List[int], li: List[int], lu: List[int]) -> int:
    result = 0
    for i in range(0, len(li)):
        for j in range(0, len(lu)):
            result += abs(arr[li[i]] - arr[lu[j]])
    return result


def min_unfairness_k1(arr: List[int], used_li: Set[int]) -> (int, int):
    min_uf = math.inf
    min_uf_li = 0
    for li in range(0, len(arr)):
        uf = unfairness(arr, [li], [lu for lu in range(0, len(arr)) if lu != li and lu not in used_li])
        if uf < min_uf:
            min_uf = uf
            min_uf_li = li
    return min_uf, min_uf_li


def min_unfairness(arr: List[int], k: int) -> int:
    assert k < len(arr)

    current_uf = 0
    used_li = set()
    for _ in range(1, k + 1):
        min_uf, min_uf_li = min_unfairness_k1(arr, used_li)
        current_uf += min_uf
        for li in used_li:
            current_uf -= abs(arr[li] - arr[min_uf_li])
        used_li.add(min_uf_li)

    return current_uf


if __name__ == '__main__':
    # print(min_unfairness(
    #     [691259308, 801371251, 345390019, 162749471, 998969126, 308205008, 430442891, 404642721, 532566673, 266540863,
    #      702197285, 749105392, 775025448, 20453591, 582291534, 132855413, 747557193, 129094259, 474372133, 788391070],
    #     11))
    print(min_unfairness([4, 3, 1, 2], 2))
    pass
