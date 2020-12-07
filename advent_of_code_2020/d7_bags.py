from typing import Dict, Set

from util.files import stripped_input_lines_from


class Bag:
    def __init__(self, color: str, possible_contents: Dict[str, int]) -> None:
        self._color = color
        self._possible_contents = possible_contents

    @property
    def possible_contents(self) -> Dict[str, int]:
        return self._possible_contents

    @property
    def color(self) -> str:
        return self._color


def read_bags_from(path: str) -> Dict[str, Bag]:
    lines = stripped_input_lines_from(path)
    bags: Dict[str, Bag] = {}
    for line in lines:
        words = line.split(' ')
        bag_color = words[0] + ' ' + words[1]
        word_index = 4
        contents: Dict[str, int] = {}
        while word_index < len(words):
            if not words[word_index].isdigit():
                break
            amount = int(words[word_index])
            content_color = words[word_index + 1] + ' ' + words[word_index + 2]
            contents[content_color] = amount
            word_index += 4
        bags[bag_color] = Bag(bag_color, contents)
    return bags


def build_indirect_containers_for(color: str, all_bags: Dict[str, Bag], current_containers: Set[str]):
    next_colors_to_check = []
    for bag in all_bags.values():
        if bag.color == color:
            continue
        if color in bag.possible_contents.keys() and bag.color not in current_containers:
            current_containers.add(bag.color)
            next_colors_to_check.append(bag.color)

    for color_to_check in next_colors_to_check:
        build_indirect_containers_for(color_to_check, all_bags, current_containers)


def number_of_indirect_containers_for(color: str, bags: Dict[str, Bag]) -> int:
    containers: Set[str] = set()
    build_indirect_containers_for(color, bags, containers)
    return len(containers)


if __name__ == '__main__':
    input_bags = read_bags_from('d7_bags_input.txt')
    print(number_of_indirect_containers_for('shiny gold', input_bags))
