from typing import List, Dict


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

    def __str__(self) -> str:
        return self.value.__str__()


def wrap_index(index: int, number_of_cups: int) -> int:
    return index % number_of_cups


def wrap_destination_cup(cup: int, number_of_cups: int) -> int:
    return cup if cup > 0 else number_of_cups


def simulate_game(initial_order: str, number_of_moves: int, number_of_cups: int = -1) -> Cup:
    assert initial_order.isdigit()
    initial_len = len(initial_order)
    assert initial_len > 1 or number_of_cups > 1
    number_of_cups = initial_len if initial_len > number_of_cups else number_of_cups

    all_cups: Dict[int, Cup] = {}
    head = None
    tail = None
    for cup_str in initial_order:
        cup_value = int(cup_str)
        cup = Cup(cup_value)
        all_cups[cup_value] = cup
        if head is None:
            head = cup
        if tail is not None:
            tail.next_cup = cup
        tail = cup

    if number_of_cups > initial_len:
        for value in range(max(*all_cups) + 1, number_of_cups + 1):
            cup = Cup(value)
            all_cups[value] = cup
            if head is None:
                head = cup
            if tail is not None:
                tail.next_cup = cup
            tail = cup
    tail.next_cup = head

    move = 0
    current_cup = head
    while move < number_of_moves:
        picked_up_cups = []
        cup_to_pick = current_cup.next_cup
        while len(picked_up_cups) < 3:
            picked_up_cups.append(cup_to_pick)
            cup_to_pick = cup_to_pick.next_cup
        current_cup.next_cup = cup_to_pick

        destination_cup_value = wrap_destination_cup(current_cup.value - 1, number_of_cups)
        while destination_cup_value in [cup.value for cup in picked_up_cups]:
            destination_cup_value = wrap_destination_cup(destination_cup_value - 1, number_of_cups)
        destination_cup = all_cups[destination_cup_value]
        destination_cup_next = destination_cup.next_cup
        destination_cup.next_cup = picked_up_cups[0]
        picked_up_cups[-1].next_cup = destination_cup_next

        current_cup = current_cup.next_cup
        move += 1

    return head


def print_cups(head: Cup):
    assert head
    print(head.value, end=',')
    head_iter = head.next_cup
    while head_iter.value != head.value:
        print(head_iter.value, end=',')
        head_iter = head_iter.next_cup
    print()


def str_clockwise_after_value(value: int, head: Cup):
    assert head
    cup_iter = head
    while cup_iter.value != value:
        cup_iter = cup_iter.next_cup
    cup_iter = cup_iter.next_cup
    result: List[int] = []
    while cup_iter.value != 1:
        result.append(cup_iter.value)
        cup_iter = cup_iter.next_cup
    return ''.join([str(cup) for cup in result])


def multiply_cups_after_value(value: int, head: Cup, number_of_cups: int = 2) -> int:
    assert number_of_cups > 0 and head
    cup_iter = head
    while cup_iter.value != value:
        cup_iter = cup_iter.next_cup
    cup_iter = cup_iter.next_cup
    result = cup_iter.value
    multiplications = 1
    while multiplications < number_of_cups:
        cup_iter = cup_iter.next_cup
        result *= cup_iter.value
        multiplications += 1
    return result


if __name__ == '__main__':
    print(
        multiply_cups_after_value(1, head=simulate_game('167248359', number_of_moves=10000000, number_of_cups=1000000)))
