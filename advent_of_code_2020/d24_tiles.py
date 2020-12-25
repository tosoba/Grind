import copy
from typing import Tuple, Dict, Set

from util.files import stripped_input_lines_from


def build_initial_flipped_states_from(path: str) -> Dict[Tuple[float, float], bool]:
    tiles_input = stripped_input_lines_from(path)
    flipped_states: Dict[Tuple[float, float], bool] = {}
    for index, line in enumerate(tiles_input):
        line_index = 0
        x_offset = 0
        y_offset = 0
        while line_index < len(line):
            char = line[line_index]
            if char == 'e':
                x_offset += 1
            elif char == 'w':
                x_offset -= 1
            elif char == 'n' or char == 's':
                y_offset += 1 if char == 'n' else -1
                line_index += 1
                char = line[line_index]
                if char == 'e':
                    x_offset += .5
                elif char == 'w':
                    x_offset -= .5
                else:
                    assert False
            else:
                assert False
            line_index += 1
        offset = (x_offset, y_offset)
        flipped_states[offset] = not flipped_states[offset] if offset in flipped_states.keys() else True
    return flipped_states


def count_flipped_to_black(flipped_states: Dict[Tuple[float, float], bool]) -> int:
    result = 0
    for flipped in flipped_states.values():
        if flipped:
            result += 1
    return result


def count_flipped_to_black_from(path: str) -> int:
    return count_flipped_to_black(build_initial_flipped_states_from(path))


def simulate_exhibit_from(path: str, days: int = 100) -> int:
    black_tiles: Set[Tuple[float, float]] = {offsets
                                             for offsets, is_black in build_initial_flipped_states_from(path).items()
                                             if is_black}
    day = 0
    neighbour_offsets = [(-1, 0), (-.5, 1), (.5, 1), (1, 0), (.5, -1), (-.5, -1)]
    while day < days:
        new_black_tiles = copy.deepcopy(black_tiles)
        for offsets in black_tiles:
            x_offset, y_offset = offsets
            black_neighbours = 0
            for x_no, y_no in neighbour_offsets:
                neighbour = x_offset + x_no, y_offset + y_no
                if neighbour in black_tiles:
                    black_neighbours += 1
            if black_neighbours == 0 or black_neighbours > 2:
                new_black_tiles.remove(offsets)

        for offsets in black_tiles:
            x_offset, y_offset = offsets
            for x_no, y_no in neighbour_offsets:
                black_neighbours = 0
                neighbour = x_offset + x_no, y_offset + y_no
                if neighbour in black_tiles:
                    continue
                for x_no_no, y_no_no in neighbour_offsets:
                    neighbour_x, neighbour_y = neighbour
                    no_no = neighbour_x + x_no_no, neighbour_y + y_no_no
                    if no_no in black_tiles:
                        black_neighbours += 1
                    if black_neighbours > 2:
                        break
                if black_neighbours == 2:
                    new_black_tiles.add(neighbour)

        day += 1
        black_tiles = new_black_tiles
    return len(black_tiles)


if __name__ == '__main__':
    print(simulate_exhibit_from('d24_tiles_input.txt'))
