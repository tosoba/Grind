from typing import List


def read_seats_grid_from(path: str) -> List[List[str]]:
    return list(map(lambda line: [char for char in line], open(path, 'r').readlines()))


def count_occupied_when_stable(path: str) -> int:
    grid = read_seats_grid_from(path)
    modified_grid = list(grid)


if __name__ == '__main__':
    print(read_seats_grid_from('d11_seats_input.txt'))
