from typing import List

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


def simulate_cycles(pocket: List[List[bool]], number_of_cycles: int = 6) -> int:
    cube: List[List[List[bool]]] = [pocket]

    def new_inactive_pocket() -> List[List[bool]]:
        return [[False] * (len(pocket[0]))] * (len(pocket))

    for cycle in range(0, number_of_cycles):
        for strip in pocket:
            strip.insert(0, False)
            strip.append(False)
        pocket.insert(0, [False] * (len(pocket[0])))
        pocket.append([False] * (len(pocket[0])))
        cube.insert(0, new_inactive_pocket())
        cube.append(new_inactive_pocket())

    active_count = 0
    for pocket in cube:
        for strip in pocket:
            for active in strip:
                if active:
                    active_count += 1
    return active_count


if __name__ == '__main__':
    simulate_cycles(read_pocket_from('d17_conway_cubes_input.txt'))
