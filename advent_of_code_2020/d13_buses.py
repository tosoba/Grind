from typing import List


def earliest_bus(timestamp: int, buses_input: str) -> int:
    buses: List[int] = []
    for b in buses_input.split(','):
        if b.isdigit():
            buses.append(int(b))

    offset = 0
    while True:
        for bus in buses:
            if (timestamp + offset) % bus == 0:
                return bus * offset
        offset += 1


if __name__ == '__main__':
    print(earliest_bus(1008833,
                       '19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,643,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17,13,x,x,x,x,23,'
                       'x,x,x,x,x,x,x,509,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29'))
