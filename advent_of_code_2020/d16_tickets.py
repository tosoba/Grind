from typing import List, Set, Iterable, Dict

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

    def all_in_any_range(self, values: Iterable[int]) -> bool:
        for value in values:
            if not self.is_in_any_range(value):
                return False
        return True

    @property
    def field(self) -> str:
        return self._field


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


def read_valid_tickets_from(path: str, constraints: List[Constraint]) -> List[List[int]]:
    valid_tickets: List[List[int]] = []
    for line in stripped_input_lines_from(path):
        values = {int(value) for value in line.split(',')}
        for constraint in constraints:
            values.difference_update(set(constraint.filter_in_any_range(values)))
            if len(values) == 0:
                valid_tickets.append([int(value) for value in line.split(',')])
                break
    return valid_tickets


def match_ticket_fields(valid_tickets: List[List[int]], constraints: List[Constraint]) -> Dict[str, int]:
    assert valid_tickets and constraints
    indices_matching_fields: Dict[str, Set[int]] = {constraint.field: set() for constraint in constraints}
    for index in range(0, len(valid_tickets[0])):
        values = [ticket[index] for ticket in valid_tickets]
        for constraint in constraints:
            if constraint.all_in_any_range(values):
                indices_matching_fields[constraint.field].add(index)
    print(indices_matching_fields)

    field_indices: Dict[str, int] = {constraint.field: -1 for constraint in constraints}
    for _ in range(0, len(constraints)):
        index_to_remove = -1
        for field, indices in indices_matching_fields.items():
            if len(indices) == 1:
                index_to_remove = indices.pop()
                field_indices[field] = index_to_remove
                break
        assert index_to_remove != -1
        for indices in indices_matching_fields.values():
            if index_to_remove in indices:
                indices.remove(index_to_remove)
    assert -1 not in field_indices.values()
    print(field_indices)
    return field_indices


def multiply_departure_field_values(ticket: List[int], field_indices: Dict[str, int]) -> int:
    result = 1
    for field, index in field_indices.items():
        if "departure" in field:
            result *= ticket[index]
    return result


if __name__ == '__main__':
    cons = read_constraints_from('d16_constraints.txt')
    tickets = read_valid_tickets_from('d16_tickets.txt', cons)
    print(multiply_departure_field_values(
        ticket=[181, 131, 61, 67, 151, 59, 113, 101, 79, 53, 71, 193, 179, 103, 149, 157, 127, 97, 73, 191],
        field_indices=match_ticket_fields(tickets, cons)))
