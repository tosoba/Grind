# https://adventofcode.com/2020/day/4
from enum import Enum
from typing import List, Dict, Set
import re

valid_ecl: Set[str] = {'amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth'}


class Field(Enum):
    byr = 1
    iyr = 2
    eyr = 3
    hgt = 4
    hcl = 5
    ecl = 6
    pid = 7
    cid = 8

    def is_value_valid(self, value: str) -> bool:
        return validation_methods[self](value)


validation_methods = {
    Field.byr: lambda value: len(value) == 4 and value.isdigit() and 1920 <= int(value) <= 2002,
    Field.iyr: lambda value: len(value) == 4 and value.isdigit() and 2010 <= int(value) <= 2020,
    Field.eyr: lambda value: len(value) == 4 and value.isdigit() and 2020 <= int(value) <= 2030,
    Field.hgt: lambda value: (
            (value[-2:] == 'cm' and len(value) == 5 and value[0:3].isdigit() and 150 <= int(value[0:3]) <= 193)
            or (value[-2:] == 'in' and value[0:2].isdigit() and len(value) == 4 and 59 <= int(value[0:2]) <= 76)
    ),
    Field.hcl: lambda value: len(value) == 7 and value[0] == '#' and (
                re.compile("[a-f0-9]+").fullmatch(value[1:]) is not None),
    Field.ecl: lambda value: value in valid_ecl,
    Field.pid: lambda value: len(value) == 9 and value.isdigit(),
    Field.cid: lambda value: True
}


def get_lines_from(path: str) -> List[str]:
    return open(path, 'r').readlines()


def reset_validation(validation: Dict[Field, bool]):
    for field in Field:
        validation[field] = False


def is_valid(validation: Dict[Field, bool]):
    return all(field == Field.cid or value for field, value in validation.items())


def count_valid_data_1(lines: List[str]) -> int:
    validation: Dict[Field, bool] = {}
    reset_validation(validation)

    valid_count = 0
    lines.append('')
    for line in lines:
        stripped = line.strip()
        if not stripped:
            if is_valid(validation):
                valid_count += 1
            reset_validation(validation)
        else:
            for fv in stripped.split(' '):
                field, _ = fv.split(':')
                validation[Field[field]] = True

    return valid_count


def count_valid_data_2(lines: List[str]) -> int:
    validation: Dict[Field, bool] = {}
    reset_validation(validation)

    valid_count = 0
    lines.append('')
    for line in lines:
        stripped = line.strip()
        if not stripped:
            if is_valid(validation):
                valid_count += 1
            reset_validation(validation)
        else:
            for fv in stripped.split(' '):
                f, v = fv.split(':')
                field = Field[f]
                validation[field] = field.is_value_valid(v)

    return valid_count


if __name__ == '__main__':
    lines_input = get_lines_from('d4_passports_input.txt')
    print(count_valid_data_2(lines_input))
