from typing import List, Set


def read_input_numbers_from(path: str) -> List[int]:
    return list(map(lambda line: int(line.strip()), open(path, 'r').readlines()))


def find_first_number_not_equal_to_sum_of_prev(number_of_prev: int, numbers: List[int]) -> int:
    if number_of_prev >= len(numbers):
        assert False

    previous_numbers: Set[int] = set()
    for number_index in range(number_of_prev):
        previous_numbers.add(numbers[number_index])

    next_number_index = number_of_prev
    number_index_to_drop_from_prev = 0
    while next_number_index < len(numbers):
        next_number = numbers[next_number_index]
        equal_to_sum_of_2_prev = False
        for previous_number in previous_numbers:
            diff = next_number - previous_number
            if diff in previous_numbers:
                equal_to_sum_of_2_prev = True
                break

        if not equal_to_sum_of_2_prev:
            return next_number

        previous_numbers.remove(numbers[number_index_to_drop_from_prev])
        number_index_to_drop_from_prev += 1
        previous_numbers.add(next_number)
        next_number_index += 1

    assert False


def solve_part_1(number_of_prev: int, path: str) -> int:
    return find_first_number_not_equal_to_sum_of_prev(number_of_prev, read_input_numbers_from(path))


if __name__ == '__main__':
    print(solve_part_1(25, 'd9_sum_of_prev_input.txt'))
