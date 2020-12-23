from typing import List, Set


class Cup:
    def __init__(self, value: int) -> None:
        self._value = value
        self._next_cup = None

    @property
    def value(self) -> int:
        return self._value

    @property
    def next_cup(self) -> 'Cup':
        return self._next_cup

    @next_cup.setter
    def next_cup(self, value):
        self._next_cup = value


def wrap_index(index: int, number_of_cups: int) -> int:
    return index % number_of_cups


def wrap_destination_cup(cup: int, number_of_cups: int) -> int:
    return cup if cup > 0 else number_of_cups


def simulate_game(initial_order: str, number_of_moves: int, number_of_cups: int = -1) -> str:
    assert initial_order.isdigit()
    initial_len = len(initial_order)
    assert initial_len > 1 or number_of_cups > 1
    number_of_cups = initial_len if initial_len > number_of_cups else number_of_cups

    all_cups: Set[Cup] = set()
    head = None
    tail = None
    for cup_str in initial_order:
        cup = Cup(int(cup_str))
        all_cups.add(cup)
        if head is None:
            head = cup
        if tail is not None:
            tail.next_cup = cup
        tail = cup
    tail.next_cup = head

    cups = [int(cup) for cup in initial_order]
    if number_of_cups > initial_len:
        for cup in range(max(cups) + 1, number_of_cups + 1):
            cups.append(cup)

    move = 0
    current_cup_index = 0
    current_cup = cups[current_cup_index]
    while move < number_of_moves:
        picked_up_cups = []
        cup_to_pick_up_index = wrap_index(current_cup_index + 1, len(cups))
        while len(picked_up_cups) < 3:
            if cup_to_pick_up_index == len(cups):
                cup_to_pick_up_index = 0
            picked_up_cups.append(cups.pop(cup_to_pick_up_index))
        destination_cup = wrap_destination_cup(current_cup - 1, number_of_cups)
        while destination_cup in picked_up_cups:
            destination_cup = wrap_destination_cup(destination_cup - 1, number_of_cups)
        destination_index = cups.index(destination_cup) + 1
        if destination_index == len(cups):
            for cup in picked_up_cups:
                cups.append(cup)
        else:
            for cup in reversed(picked_up_cups):
                cups.insert(destination_index, cup)
        current_cup_index = wrap_index(cups.index(current_cup) + 1, len(cups))
        current_cup = cups[current_cup_index]
        move += 1

    return cups_clockwise_after_1(cups)


def cups_clockwise_after_1(cups: List[int]) -> str:
    cup_index = (cups.index(1) + 1) % len(cups)
    result: List[int] = []
    while cups[cup_index] != 1:
        result.append(cups[cup_index])
        cup_index = (cup_index + 1) % len(cups)
    return ''.join([str(cup) for cup in result])


def multiply_star_cups(cups: List[int]) -> int:
    cup1_index = cups.index(1)
    return cups[(cup1_index + 1) % len(cups)] * cups[(cup1_index + 2) % len(cups)]


if __name__ == '__main__':
    print(simulate_game('389125467', number_of_moves=100))
