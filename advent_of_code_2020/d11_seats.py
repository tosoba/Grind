from typing import List, Callable, Tuple


def read_seats_grid_from(path: str) -> List[List[str]]:
    return list(map(lambda line: [char for char in line.strip()], open(path, 'r').readlines()))


def count_adjacent_occupied(grid: List[List[str]], i: int, j: int) -> int:
    occupied = 0
    if i - 1 >= 0:
        if grid[i - 1][j] == '#':
            occupied += 1
        if j - 1 >= 0 and grid[i - 1][j - 1] == '#':
            occupied += 1
        if j + 1 < len(grid[i - 1]) and grid[i - 1][j + 1] == '#':
            occupied += 1
    if i + 1 < len(grid):
        if grid[i + 1][j] == '#':
            occupied += 1
        if j - 1 >= 0 and grid[i + 1][j - 1] == '#':
            occupied += 1
        if j + 1 < len(grid[i + 1]) and grid[i + 1][j + 1] == '#':
            occupied += 1
    if j - 1 >= 0 and grid[i][j - 1] == '#':
        occupied += 1
    if j + 1 < len(grid[i]) and grid[i][j + 1] == '#':
        occupied += 1
    return occupied


def can_see_occupied_east(grid: List[List[str]], i: int, j: int) -> bool:
    index = j + 1
    can_see = False
    while index < len(grid[i]):
        if grid[i][index] == '#':
            can_see = True
            break
        elif grid[i][index] == 'L':
            can_see = False
            break
        index += 1
    return can_see


def can_see_occupied_west(grid: List[List[str]], i: int, j: int) -> bool:
    index = j - 1
    can_see = False
    while index >= 0:
        if grid[i][index] == '#':
            can_see = True
            break
        elif grid[i][index] == 'L':
            can_see = False
            break
        index -= 1
    return can_see


def can_see_occupied_north(grid: List[List[str]], i: int, j: int) -> bool:
    index = i - 1
    can_see = False
    while index >= 0:
        if grid[index][j] == '#':
            can_see = True
            break
        elif grid[index][j] == 'L':
            can_see = False
            break
        index -= 1
    return can_see


def can_see_occupied_south(grid: List[List[str]], i: int, j: int) -> bool:
    index = i + 1
    can_see = False
    while index < len(grid):
        if grid[index][j] == '#':
            can_see = True
            break
        elif grid[index][j] == 'L':
            can_see = False
            break
        index += 1
    return can_see


def can_see_occupied_south_east(grid: List[List[str]], i: int, j: int) -> bool:
    index1 = i + 1
    index2 = j + 1
    can_see = False
    while index1 < len(grid) and index2 < len(grid[index1]):
        if grid[index1][index2] == '#':
            can_see = True
            break
        elif grid[index1][index2] == 'L':
            can_see = False
            break
        index1 += 1
        index2 += 1
    return can_see


def can_see_occupied_south_west(grid: List[List[str]], i: int, j: int) -> bool:
    index1 = i + 1
    index2 = j - 1
    can_see = False
    while index1 < len(grid) and index2 >= 0:
        if grid[index1][index2] == '#':
            can_see = True
            break
        elif grid[index1][index2] == 'L':
            can_see = False
            break
        index1 += 1
        index2 -= 1
    return can_see


def can_see_occupied_north_east(grid: List[List[str]], i: int, j: int) -> bool:
    index1 = i - 1
    index2 = j + 1
    can_see = False
    while index1 >= 0 and index2 < len(grid[index1]):
        if grid[index1][index2] == '#':
            can_see = True
            break
        elif grid[index1][index2] == 'L':
            can_see = False
            break
        index1 -= 1
        index2 += 1
    return can_see


def can_see_occupied_north_west(grid: List[List[str]], i: int, j: int) -> bool:
    index1 = i - 1
    index2 = j - 1
    can_see = False
    while index1 >= 0 and index2 >= 0:
        if grid[index1][index2] == '#':
            can_see = True
            break
        elif grid[index1][index2] == 'L':
            can_see = False
            break
        index1 -= 1
        index2 -= 1
    return can_see


def modify_grid_1(grid_before: List[List[str]]) -> (bool, List[List[str]]):
    grid_after = [row[:] for row in grid_before]
    grid_changed = False
    for i in range(len(grid_before)):
        for j in range(len(grid_before[i])):
            if grid_before[i][j] == '.':
                continue
            adjacent_occupied = count_adjacent_occupied(grid_before, i, j)
            if grid_before[i][j] == 'L' and adjacent_occupied == 0:
                grid_after[i][j] = '#'
                grid_changed = True
            elif grid_before[i][j] == '#' and adjacent_occupied >= 4:
                grid_after[i][j] = 'L'
                grid_changed = True
    return grid_changed, grid_after


def count_occupied_seen(grid: List[List[str]], i: int, j: int,
                        can_see_funcs: List[Callable[[List[List[str]], int, int], bool]]) -> int:
    occupied_seen = 0
    func_index = 0
    while func_index < len(can_see_funcs):
        if can_see_funcs[func_index](grid, i, j):
            occupied_seen += 1
        func_index += 1
    return occupied_seen


def modify_grid_2(grid_before: List[List[str]]) -> (bool, List[List[str]]):
    grid_after = [row[:] for row in grid_before]
    grid_changed = False
    can_see_funcs = [can_see_occupied_east, can_see_occupied_west, can_see_occupied_north, can_see_occupied_south,
                     can_see_occupied_north_east, can_see_occupied_north_west, can_see_occupied_south_east,
                     can_see_occupied_south_west]
    for i in range(len(grid_before)):
        for j in range(len(grid_before[i])):
            if grid_before[i][j] == '.':
                continue
            occupied_seen = count_occupied_seen(grid_before, i, j, can_see_funcs)
            if grid_before[i][j] == 'L' and occupied_seen == 0:
                grid_after[i][j] = '#'
                grid_changed = True
            elif grid_before[i][j] == '#' and occupied_seen >= 5:
                grid_after[i][j] = 'L'
                grid_changed = True
    return grid_changed, grid_after


def count_occupied_when_stable(path: str,
                               modify_grid: Callable[[List[List[str]]], Tuple[bool, List[List[str]]]]) -> int:
    grid_before = read_seats_grid_from(path)
    assert grid_before
    grid_changed = True
    while grid_changed:
        grid_changed, grid_before = modify_grid(grid_before)
    occupied = 0
    for row in grid_before:
        for seat in row:
            if seat == '#':
                occupied += 1
    return occupied


def print_grid(grid: List[List[str]]):
    for row in grid:
        print(''.join(row))
    print()


if __name__ == '__main__':
    print(count_occupied_when_stable('d11_seats_input.txt', modify_grid_2))
