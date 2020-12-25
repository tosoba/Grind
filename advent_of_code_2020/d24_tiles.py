from typing import Tuple, Dict

from util.files import stripped_input_lines_from


def count_flipped_to_black_from(path: str) -> int:
    tiles_input = stripped_input_lines_from(path)
    flipped_states: Dict[Tuple[int, int], bool] = {}
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

    result = 0
    for flipped in flipped_states.values():
        if flipped:
            result += 1
    return result


if __name__ == '__main__':
    print(count_flipped_to_black_from('d24_tiles_input.txt'))
