# https://adventofcode.com/2019/day/1

from util.files import stripped_input_lines_from


def calculate_fuel(mass: int) -> int:
    return (mass // 3) - 2


def calculate_fuel_2(mass: int) -> int:
    fuel = calculate_fuel(mass)
    total = 0
    while fuel > 0:
        total += fuel
        fuel = calculate_fuel(fuel)
    return total


def calculate_total_fuel_1(path: str) -> int:
    total = 0
    for line in stripped_input_lines_from(path):
        total += calculate_fuel(int(line))
    return total


def calculate_total_fuel_2(path: str) -> int:
    total = 0
    for line in stripped_input_lines_from(path):
        total += calculate_fuel_2(int(line))
    return total


if __name__ == '__main__':
    print(calculate_total_fuel_2('d1_2019.txt'))
