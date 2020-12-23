from typing import List


def simulate_game(initial_order: str, number_of_moves: int = 100) -> str:
    assert initial_order.isdigit()
    initial_len = len(initial_order)

    def wrap_destination_cup(cup: int) -> int:
        return cup if cup > 0 else initial_len

    def wrap_index(index: int) -> int:
        return index % len(cups)

    cups = [int(cup) for cup in initial_order]
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
        while destination_cup not in cups:
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

    cup_index = wrap_index(cups.index(1) + 1)
    result: List[int] = []
    while cups[cup_index] != 1:
        result.append(cups[cup_index])
        cup_index = wrap_index(cup_index + 1)
    return ''.join([str(cup) for cup in result])


if __name__ == '__main__':
    print(simulate_game('167248359'))
