# https://adventofcode.com/2020/day/3
from typing import List
import functools


def parse_input(path: str) -> List[str]:
    return list(map(lambda line: line.strip(), open(path, 'r').readlines()))


def get_char_at(index: int, line: str) -> str:
    return line[index % len(line)]


def count_trees(lines: List[str], offset_x: int, offset_y: int) -> int:
    y = offset_y
    x = offset_x
    trees = 0
    while y < len(lines):
        if get_char_at(x, lines[y]) == '#':
            trees += 1
        y += offset_y
        x += offset_x
    return trees


if __name__ == '__main__':
    lines_input = parse_input('d3_slope_input.txt')
    trees_part_1 = count_trees(lines_input, 3, 1)
    trees_part_2 = functools.reduce(lambda e1, e2: e1 * e2,
                                    list(map(lambda offsets: count_trees(lines_input, offsets[0], offsets[1]),
                                             [(1, 1), (3, 1), (5, 1), (7, 1), (1, 2)])))
    print(trees_part_1)
    print(trees_part_2)
