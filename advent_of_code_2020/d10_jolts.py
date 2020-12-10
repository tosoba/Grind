from typing import List


def read_input_numbers_from(path: str) -> List[int]:
    return list(map(lambda line: int(line.strip()), open(path, 'r').readlines()))


def build_joltage_list(path: str) -> List[int]:
    joltage_list = read_input_numbers_from(path)
    joltage_list.sort()
    outlet_joltage = joltage_list[-1] + 3
    joltage_list.insert(0, 0)
    joltage_list.append(outlet_joltage)
    return joltage_list


def solve_part_1(path: str) -> int:
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


def solve(joltage_list: List[int], start: int) -> int:
    pass


def solve_part_2(path: str) -> int:
    return solve(build_joltage_list(path), 1)


if __name__ == '__main__':
    print(solve_part_1('d10_jolts_input.txt'))
