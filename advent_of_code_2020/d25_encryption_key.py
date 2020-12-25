def get_loop_size(public_key: int) -> int:
    initial_subject_number = 7
    divider = 20201227
    value = 1
    loop = 0
    while value != public_key:
        value *= initial_subject_number
        value %= divider
        loop += 1
    return loop


def calculate_encryption_key(card_public_key: int, door_public_key: int) -> int:
    card_loop_size = get_loop_size(card_public_key)
    divider = 20201227
    value = 1
    for _ in range(0, card_loop_size):
        value *= door_public_key
        value %= divider
    return value


if __name__ == '__main__':
    print(calculate_encryption_key(2448427, 6929599))
