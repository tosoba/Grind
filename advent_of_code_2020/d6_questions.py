# https://adventofcode.com/2020/day/6
from typing import List, Set


def stripped_input_lines_from(path: str) -> List[str]:
    return list(map(lambda line: line.strip(), open(path, 'r').readlines()))


def count_yes_answers_1(path: str) -> int:
    lines_input = stripped_input_lines_from(path)
    result = 0
    answers: Set[str] = set()
    for line in lines_input:
        if not line:
            result += len(answers)
            answers.clear()
        else:
            for char in line:
                answers.add(char)
    return result + len(answers)


def count_yes_answers_2(path: str) -> int:
    lines_input = stripped_input_lines_from(path)
    result = 0
    answers: Set[str] = set()
    first_in_group = True
    for line in lines_input:
        if not line:
            result += len(answers)
            answers.clear()
            first_in_group = True
        elif first_in_group:
            for char in line:
                answers.add(char)
            first_in_group = False
        else:
            ans: Set[str] = set()
            for char in line:
                ans.add(char)
            answers.intersection_update(ans)

    return result + len(answers)


if __name__ == '__main__':
    print(count_yes_answers_2('d6_questions_input.txt'))
