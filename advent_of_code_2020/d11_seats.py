from typing import List


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


def modify_grid(grid_before: List[List[str]]) -> (bool, List[List[str]]):
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


def count_occupied_when_stable(path: str) -> int:
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
    print(count_occupied_when_stable('d11_seats_input.txt'))
