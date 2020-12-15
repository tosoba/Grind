from typing import Dict


def simulate_game_from(input_numbers: str, turns_limit: int = 2020) -> int:
    assert input_numbers
    numbers = [int(n) for n in input_numbers.split(',')]
    game_state: Dict[int, (int, int, int)] = {}
    turn_number = 1
    for num in numbers:
        game_state[num] = 1, turn_number, -1
        turn_number += 1
    last_spoken_number = numbers[-1]

    while turn_number <= turns_limit:
        times_spoken, latest_round_spoken, prev_round_spoken = game_state[last_spoken_number]
        next_spoken_number = 0
        if times_spoken > 1:
            next_spoken_number = latest_round_spoken - prev_round_spoken

        if next_spoken_number in game_state:
            next_spoken_times, prev_round_number, _ = game_state[next_spoken_number]
            game_state[next_spoken_number] = next_spoken_times + 1, turn_number, prev_round_number
        else:
            game_state[next_spoken_number] = 1, turn_number, -1

        last_spoken_number = next_spoken_number
        turn_number += 1

    return last_spoken_number


if __name__ == '__main__':
    print(simulate_game_from('12,1,16,3,11,0', 30000000))
