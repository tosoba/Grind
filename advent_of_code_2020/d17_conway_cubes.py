import copy
from typing import List, Tuple

from util.files import stripped_input_lines_from


def read_pocket_from(path: str) -> List[List[bool]]:
    pocket: List[List[bool]] = []
    for line in stripped_input_lines_from(path):
        strip: List[bool] = []
        pocket.append(strip)
        for char in line:
            if char == '#':
                strip.append(True)
            elif char == '.':
                strip.append(False)
            else:
                assert False
    return pocket


def print_pocket(pocket: List[List[bool]]):
    for strip in pocket:
        for active in strip:
            print('#' if active else '.', end='')
        print()


def neighbours_coordinates_3d(x: int, y: int, z: int,
                              cube: List[List[List[bool]]],
                              neighbour_offsets: List[Tuple[int, int, int]]) -> List[Tuple[int, int, int]]:
    return [(x + x_offset, y + y_offset, z + z_offset)
            for x_offset, y_offset, z_offset in neighbour_offsets
            if 0 <= x + x_offset < len(cube)
            and 0 <= y + y_offset < len(cube[0])
            and 0 <= z + z_offset < len(cube[0][0])]


def neighbours_coordinates_4d(x: int, y: int, z: int, w: int,
                              hyper_cube: List[List[List[List[bool]]]],
                              neighbour_offsets: List[Tuple[int, int, int, int]]) -> List[Tuple[int, int, int, int]]:
    return [(x + x_offset, y + y_offset, z + z_offset, w + w_offset)
            for x_offset, y_offset, z_offset, w_offset in neighbour_offsets
            if 0 <= x + x_offset < len(hyper_cube)
            and 0 <= y + y_offset < len(hyper_cube[0])
            and 0 <= z + z_offset < len(hyper_cube[0][0])
            and 0 <= w + w_offset < len(hyper_cube[0][0][0])]


def simulate_3d_cycles(initial_pocket: List[List[bool]], number_of_cycles: int = 6) -> int:
    cube: List[List[List[bool]]] = [initial_pocket]
    neighbour_offsets = [(x, y, z)
                         for x in range(-1, 2)
                         for y in range(-1, 2)
                         for z in range(-1, 2)
                         if x != 0 or y != 0 or z != 0]

    def new_inactive_pocket() -> List[List[bool]]:
        return [[False for _ in range(len(cube[0][0]))] for _ in range(len(cube[0]))]

    for cycle in range(0, number_of_cycles):
        for pocket in cube:
            pocket.insert(0, [False] * len(pocket[0]))
            pocket.append([False] * len(pocket[0]))

            for strip in pocket:
                strip.insert(0, False)
                strip.append(False)

        cube.insert(0, new_inactive_pocket())
        cube.append(new_inactive_pocket())

        modified_cube = copy.deepcopy(cube)
        for x, pocket in enumerate(cube):
            for y, strip in enumerate(pocket):
                for z, active in enumerate(strip):
                    neighbours = neighbours_coordinates_3d(x, y, z, cube, neighbour_offsets)
                    if active:
                        active_count = 0
                        for nx, ny, nz in neighbours:
                            if cube[nx][ny][nz]:
                                active_count += 1
                            if active_count > 3:
                                break
                        modified_cube[x][y][z] = active_count == 2 or active_count == 3
                    else:
                        active_count = 0
                        for nx, ny, nz in neighbours:
                            if cube[nx][ny][nz]:
                                active_count += 1
                            if active_count > 3:
                                break
                        modified_cube[x][y][z] = active_count == 3
        cube = modified_cube

    active_count = 0
    for pocket in cube:
        for strip in pocket:
            for active in strip:
                if active:
                    active_count += 1
    return active_count


def simulate_4d_cycles(initial_pocket: List[List[bool]], number_of_cycles: int = 6) -> int:
    hyper_cube = [[initial_pocket]]
    neighbour_offsets = [(x, y, z, w)
                         for x in range(-1, 2)
                         for y in range(-1, 2)
                         for z in range(-1, 2)
                         for w in range(-1, 2)
                         if x != 0 or y != 0 or z != 0 or w != 0]

    def new_inactive_pocket() -> List[List[bool]]:
        return [[False for _ in range(len(hyper_cube[0][0][0]))] for _ in range(len(hyper_cube[0][0]))]

    def new_inactive_cube() -> List[List[List[bool]]]:
        return [[[False for _ in range(len(hyper_cube[0][0][0]))]
                 for _ in range(len(hyper_cube[0][0]))]
                for _ in range(len(hyper_cube[0]))]

    for cycle in range(0, number_of_cycles):
        for cube in hyper_cube:
            for pocket in cube:
                pocket.insert(0, [False] * len(pocket[0]))
                pocket.append([False] * len(pocket[0]))

                for strip in pocket:
                    strip.insert(0, False)
                    strip.append(False)

            cube.insert(0, new_inactive_pocket())
            cube.append(new_inactive_pocket())

        hyper_cube.insert(0, new_inactive_cube())
        hyper_cube.append(new_inactive_cube())

        modified_hyper_cube = copy.deepcopy(hyper_cube)
        for x, cube in enumerate(hyper_cube):
            for y, pocket in enumerate(cube):
                for z, strip in enumerate(pocket):
                    for w, active in enumerate(strip):
                        neighbours = neighbours_coordinates_4d(x, y, z, w, hyper_cube, neighbour_offsets)
                        if active:
                            active_count = 0
                            for nx, ny, nz, nw in neighbours:
                                if hyper_cube[nx][ny][nz][nw]:
                                    active_count += 1
                                if active_count > 3:
                                    break
                            modified_hyper_cube[x][y][z][w] = active_count == 2 or active_count == 3
                        else:
                            active_count = 0
                            for nx, ny, nz, nw in neighbours:
                                if hyper_cube[nx][ny][nz][nw]:
                                    active_count += 1
                                if active_count > 3:
                                    break
                            modified_hyper_cube[x][y][z][w] = active_count == 3
        hyper_cube = modified_hyper_cube

    active_count = 0
    for cube in hyper_cube:
        for pocket in cube:
            for strip in pocket:
                for active in strip:
                    if active:
                        active_count += 1
    return active_count


if __name__ == '__main__':
    print(simulate_4d_cycles(read_pocket_from('d17_conway_cubes_input.txt')))
