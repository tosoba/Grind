from typing import List

from util.files import stripped_input_lines_from


def calculate_winner_score(player1_path: str, player2_path: str) -> int:
    player1_cards: List[int] = [int(line) for line in stripped_input_lines_from(player1_path)]
    player2_cards: List[int] = [int(line) for line in stripped_input_lines_from(player2_path)]
    assert player1_cards and player2_cards
    while player1_cards and player2_cards:
        player1_card = player1_cards.pop(0)
        player2_card = player2_cards.pop(0)
        if player1_card > player2_card:
            player1_cards.append(player1_card)
            player1_cards.append(player2_card)
        elif player2_card > player1_card:
            player2_cards.append(player2_card)
            player2_cards.append(player1_card)
        else:
            assert False
    score = 0
    for index, card in enumerate(reversed(player1_cards if player1_cards else player2_cards)):
        score += (index + 1) * card
    return score


if __name__ == '__main__':
    print(calculate_winner_score('d20_combat_player1.txt', 'd20_combat_player2.txt'))
