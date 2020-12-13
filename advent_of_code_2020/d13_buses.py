from typing import List, Dict
from collections import OrderedDict


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


def find_timestamp_for_departures_sync_with_positions(buses_input: str) -> int:
    buses: Dict[int, int] = OrderedDict()
    previous_bus_index = 0
    for index, b in enumerate(buses_input.split(',')):
        if b.isdigit():
            buses[int(b)] = index - previous_bus_index
            previous_bus_index = index

    timestamp = 0
    found = False
    while not found:
        timestamp += 1
        current_timestamp = timestamp
        found = True
        for bus, position in buses.items():
            current_timestamp += position
            if current_timestamp % bus != 0:
                found = False
                break
    return timestamp


if __name__ == '__main__':
    print(find_timestamp_for_departures_sync_with_positions(
        '19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,643,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17,13,x,x,x,x,23,x,x,x,x,x,x,x,'
        '509,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29'))
