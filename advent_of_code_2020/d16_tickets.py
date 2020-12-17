from typing import List, Set, Iterable

from util.files import stripped_input_lines_from


class Constraint:
    def __init__(self, input_str: str) -> None:
        assert input_str
        colon_index = input_str.index(':')
        self._field = input_str[:colon_index]
        range_1_str, range_2_str = input_str[colon_index + 1:].strip().split(' or ')
        self._range_1 = self._range_from(range_1_str)
        self._range_2 = self._range_from(range_2_str)

    @staticmethod
    def _range_from(input_str: str) -> (int, int):
        assert input_str
        left, right = input_str.split('-')
        return int(left), int(right)

    def is_in_any_range(self, value: int) -> bool:
        return self._range_1[0] <= value <= self._range_1[1] or self._range_2[0] <= value <= self._range_2[1]

    def filter_in_any_range(self, values: Iterable[int]) -> Iterable[int]:
        return filter(lambda value: self.is_in_any_range(value), values)


def read_constraints_from(path: str) -> List[Constraint]:
    constraints: List[Constraint] = []
    for line in stripped_input_lines_from(path):
        constraints.append(Constraint(line))
    return constraints


def check_values_uniqueness(path: str) -> bool:
    for line in stripped_input_lines_from(path):
        values: Set[str] = set()
        for value in line.split(','):
            if value in values:
                return False
            else:
                values.add(value)
    return True


def validate_tickets_from(path: str, constraints: List[Constraint]) -> int:
    error_rate = 0
    for line in stripped_input_lines_from(path):
        values = {int(value) for value in line.split(',')}
        for constraint in constraints:
            values.difference_update(set(constraint.filter_in_any_range(values)))
            if len(values) == 0:
                break
        for value in values:
            error_rate += value
    return error_rate


if __name__ == '__main__':
    print(validate_tickets_from('d16_tickets.txt', read_constraints_from('d16_constraints.txt')))
