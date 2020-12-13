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
    max_bus = 0
    max_bus_index = 0
    for index, bus in enumerate(buses_input.split(',')):
        if not bus.isdigit():
            continue
        if int(bus) > max_bus:
            max_bus = int(bus)
            max_bus_index = index

    buses: Dict[int, int] = OrderedDict()
    first_bus_offset = -1
    for index, bus in enumerate(buses_input.split(',')):
        if first_bus_offset == -1:
            first_bus_offset = index - max_bus_index
        if bus.isdigit() and int(bus) != max_bus:
            buses[int(bus)] = index - max_bus_index

    timestamp = 0
    found = False
    while not found:
        timestamp += max_bus
        found = True
        for bus, max_bus_index_offset in buses.items():
            current_timestamp = timestamp + max_bus_index_offset
            if current_timestamp % bus != 0:
                found = False
                break
    return timestamp + first_bus_offset


if __name__ == '__main__':
    print(find_timestamp_for_departures_sync_with_positions(
        '19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,643,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17,13,x,x,x,x,23,x,x,x,x,x,x,x,'
        '509,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29'))
