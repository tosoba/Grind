# https://adventofcode.com/2020/day/5
from typing import List, Dict
import math

from util.files import stripped_input_lines_from


def calculate_index(input_line: str, upper: int, lower: int, dec_upper_letter: str, inc_lower_letter: str) -> int:
    assert len(input_line) == int(math.log2(upper + 1))
    upper_bound = upper
    lower_bound = lower
    for letter in input_line[:-1]:
        diff = (((upper_bound - lower_bound) // 2) + 1)
        if letter == dec_upper_letter:
            upper_bound -= diff
        elif letter == inc_lower_letter:
            lower_bound += diff
        else:
            assert False
    if input_line[-1] == dec_upper_letter:
        return lower_bound
    elif input_line[-1] == inc_lower_letter:
        return upper_bound
    assert False


def calculate_row(input_line: str) -> int:
    return calculate_index(input_line[:7], 127, 0, 'F', 'B')


def calculate_column(input_line: str) -> int:
    return calculate_index(input_line[-3:], 7, 0, 'L', 'R')


def calculate_seat_id(input_line: str) -> int:
    return 8 * calculate_row(input_line) + calculate_column(input_line)


def get_max_seat_id_from(path: str) -> int:
    max_seat_id = 0
    for input_line in stripped_input_lines_from(path):
        assert len(input_line) == 10
        seat_id = 8 * calculate_row(input_line) + calculate_column(input_line)
        if seat_id > max_seat_id:
            max_seat_id = seat_id
    return max_seat_id


def build_seat_id_list(path: str) -> List[int]:
    result: List[int] = []
    for input_line in stripped_input_lines_from(path):
        assert len(input_line) == 10
        result.append(8 * calculate_row(input_line) + calculate_column(input_line))
    return result


def find_last_missing_seat(path: str) -> int:
    seat_ids = build_seat_id_list(path)
    seat_occurrences: Dict[int, bool] = {}
    for sid in range(max(seat_ids) + 1):
        seat_occurrences[sid] = False
    for sid in seat_ids:
        seat_occurrences[sid] = True
    missing_seat_ids: List[int] = []
    for sid, occurred in seat_occurrences.items():
        if not occurred:
            missing_seat_ids.append(sid)
    return missing_seat_ids[-1]


if __name__ == '__main__':
    print(find_last_missing_seat('d5_boarding_input.txt'))
