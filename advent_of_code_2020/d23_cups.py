from typing import List


def simulate_game(initial_order: str, number_of_moves: int, number_of_cups: int = -1) -> str:
    assert initial_order.isdigit()
    initial_len = len(initial_order)

    def wrap_destination_cup(cup: int) -> int:
        return cup if cup > 0 else max(initial_len, number_of_cups)

    def wrap_index(index: int) -> int:
        return index % len(cups)

    cups = [int(cup) for cup in initial_order]
    if number_of_cups > initial_len:
        for cup in range(max(cups) + 1, number_of_cups + 1):
            cups.append(cup)

    move = 0
    current_cup_index = 0
    current_cup = cups[current_cup_index]
    while move < number_of_moves:
        picked_up_cups = []
        cup_to_pick_up_index = wrap_index(current_cup_index + 1)
        while len(picked_up_cups) < 3:
            if cup_to_pick_up_index == len(cups):
                cup_to_pick_up_index = 0
            picked_up_cups.append(cups.pop(cup_to_pick_up_index))
        destination_cup = wrap_destination_cup(current_cup - 1)
        while destination_cup in picked_up_cups:
            destination_cup = wrap_destination_cup(destination_cup - 1)
        destination_index = cups.index(destination_cup) + 1
        if destination_index == len(cups):
            for cup in picked_up_cups:
                cups.append(cup)
        else:
            for cup in reversed(picked_up_cups):
                cups.insert(destination_index, cup)
        current_cup_index = wrap_index(cups.index(current_cup) + 1)
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
