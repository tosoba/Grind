from typing import List


def stripped_input_lines_from(path: str) -> List[str]:
    return list(map(lambda line: line.strip(), open(path, 'r').readlines()))
