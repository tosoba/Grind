from copy import deepcopy
from typing import List, Tuple, Set


def play_combat(player1_path: str, player2_path: str) -> int:
    player1_cards = [int(line.strip()) for line in open(player1_path, 'r').readlines()]
    player2_cards = [int(line.strip()) for line in open(player2_path, 'r').readlines()]
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


def play_combat_recursive(player1_cards: List[int], player2_cards: List[int]) -> Tuple[bool, int]:
    assert player1_cards and player2_cards
    previous_round_hashes: Set[Tuple[int, int]] = set()
    winning_player = 0
    while player1_cards and player2_cards:
        round_hash = hash(tuple(player1_cards)), hash(tuple(player2_cards))
        if round_hash in previous_round_hashes:
            winning_player = 1
            break
        else:
            previous_round_hashes.add(round_hash)

        player1_card = player1_cards.pop(0)
        player2_card = player2_cards.pop(0)
        if player1_card <= len(player1_cards) and player2_card <= len(player2_cards):
            player1_won_sub_game, _ = play_combat_recursive(deepcopy(player1_cards[:player1_card]),
                                                            deepcopy(player2_cards[:player2_card]))
            if player1_won_sub_game:
                player1_cards.append(player1_card)
                player1_cards.append(player2_card)
            else:
                player2_cards.append(player2_card)
                player2_cards.append(player1_card)
        else:
            if player1_card > player2_card:
                player1_cards.append(player1_card)
                player1_cards.append(player2_card)
            elif player2_card > player1_card:
                player2_cards.append(player2_card)
                player2_cards.append(player1_card)
            else:
                assert False
    if winning_player == 0:
        winning_player = 1 if player1_cards else 2
    assert winning_player == 1 or winning_player == 2

    score = 0
    for index, card in enumerate(reversed(player1_cards if winning_player == 1 else player2_cards)):
        score += (index + 1) * card

    return winning_player == 1, score


if __name__ == '__main__':
    print(play_combat_recursive([int(line.strip()) for line in open('d22_combat_player1.txt', 'r').readlines()],
                                [int(line.strip()) for line in open('d22_combat_player2.txt', 'r').readlines()]))
