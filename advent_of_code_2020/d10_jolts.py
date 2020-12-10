# https://adventofcode.com/2020/day/10
from typing import List, Dict


def read_input_numbers_from(path: str) -> List[int]:
    return list(map(lambda line: int(line.strip()), open(path, 'r').readlines()))


def build_joltage_list(path: str) -> List[int]:
    joltage_list = read_input_numbers_from(path)
    joltage_list.sort()
    outlet_joltage = joltage_list[-1] + 3
    joltage_list.insert(0, 0)
    joltage_list.append(outlet_joltage)
    return joltage_list


def count_1_and_3_diffs(path: str) -> int:
    joltage_list = build_joltage_list(path)
    diff_1_count = 0
    diff_3_count = 0
    for i in range(len(joltage_list) - 1):
        diff = joltage_list[i + 1] - joltage_list[i]
        if diff == 1:
            diff_1_count += 1
        elif diff == 3:
            diff_3_count += 1
    return diff_1_count * diff_3_count


def count_unique_arrangements(path: str) -> int:
    joltage_list = build_joltage_list(path)
    arrangements_up_to_elements: Dict[int, int] = {0: 1}
    for i in range(1, len(joltage_list)):
        j = i - 1
        arrangements_up_to_ith_element = 0
        while j >= 0 and joltage_list[i] - joltage_list[j] <= 3:
            arrangements_up_to_ith_element += arrangements_up_to_elements[joltage_list[j]]
            j -= 1
        arrangements_up_to_elements[joltage_list[i]] = arrangements_up_to_ith_element
    return arrangements_up_to_elements[joltage_list[-1]]


if __name__ == '__main__':
    print(count_unique_arrangements('d10_jolts_input.txt'))
