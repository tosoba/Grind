from typing import List

from util.files import stripped_input_lines_from


class Content:
    def __init__(self, color: str, amount: int) -> None:
        self._color = color
        self._amount = amount

    @property
    def amount(self) -> int:
        return self._amount

    @property
    def color(self) -> str:
        return self._color


class Bag:
    def __init__(self, color: str, possible_contents: List[Content]) -> None:
        self._color = color
        self._possible_contents = possible_contents

    @property
    def possible_contents(self):
        return self._possible_contents


def read_bags_from(path: str) -> List[Bag]:
    lines = stripped_input_lines_from(path)
    bags: List[Bag] = []
    for line in lines:
        words = line.split(' ')
        bag_color = words[0] + ' ' + words[1]
        word_index = 4
        contents: List[Content] = []
        while word_index < len(line):
            amount = int(words[word_index])
            content_color = words[word_index + 1] + ' ' + words[word_index + 2]
            contents.append(Content(content_color, amount))
            word_index += 4
        bags.append(Bag(bag_color, contents))
    return bags


def build_indirect_containers_for(color: str, bags: List[Bag], containers: List[Bag]) -> int:
    if not bags:
        return containers


def number_of_indirect_containers_for(color: str, bags: List[Bag]) -> int:
    return build_indirect_containers_for(color, bags, [])
