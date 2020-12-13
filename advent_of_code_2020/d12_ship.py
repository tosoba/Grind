from typing import List, Tuple


def read_movements_from(path: str) -> List[Tuple[str, int]]:
    return list(map(lambda line: (line[0], int(line.strip()[1:])), open(path, 'r').readlines()))


def turn_by_degrees(degrees: int, direction: str, current_heading: str) -> str:
    if degrees == 270:
        degrees = 90
        if direction == 'L':
            direction = 'R'
        elif direction == 'R':
            direction = 'L'
    if degrees == 180:
        if current_heading == 'W':
            return 'E'
        if current_heading == 'E':
            return 'W'
        if current_heading == 'S':
            return 'N'
        if current_heading == 'N':
            return 'S'
        assert False
    elif degrees == 90:
        if direction == 'L':
            if current_heading == 'W':
                return 'S'
            if current_heading == 'E':
                return 'N'
            if current_heading == 'S':
                return 'E'
            if current_heading == 'N':
                return 'W'
        elif direction == 'R':
            if current_heading == 'W':
                return 'N'
            if current_heading == 'E':
                return 'S'
            if current_heading == 'S':
                return 'W'
            if current_heading == 'N':
                return 'E'
        assert False
    else:
        assert False


def count_manhattan_of_movements_1(path: str) -> int:
    movements = read_movements_from(path)
    heading = 'E'
    offset_ew = 0
    offset_ns = 0
    for movement in movements:
        code, value = movement
        if code == 'N':
            offset_ns += value
        elif code == 'S':
            offset_ns -= value
        elif code == 'E':
            offset_ew += value
        elif code == 'W':
            offset_ew -= value
        elif code == 'L' or code == 'R':
            heading = turn_by_degrees(value, code, heading)
        elif code == 'F':
            if heading == 'E':
                offset_ew += value
            elif heading == 'W':
                offset_ew -= value
            elif heading == 'N':
                offset_ns += value
            elif heading == 'S':
                offset_ns -= value
            else:
                assert False
        else:
            assert False
    return abs(offset_ns) + abs(offset_ew)


def modify_waypoint(waypoint_ew: int, waypoint_ns: int, degrees: int, direction: str) -> (int, int):
    if waypoint_ns == 0 and waypoint_ew == 0:
        return waypoint_ew, waypoint_ns

    if degrees == 270:
        degrees = 90
        if direction == 'L':
            direction = 'R'
        elif direction == 'R':
            direction = 'L'
        else:
            assert False

    if degrees == 180:
        return -waypoint_ew, -waypoint_ns
    elif degrees == 90:
        waypoint_ew, waypoint_ns = waypoint_ns, waypoint_ew
        if direction == 'L':
            return -waypoint_ew, waypoint_ns
        elif direction == 'R':
            return waypoint_ew, -waypoint_ns
        assert False
    assert False


def count_manhattan_of_movements_2(path: str) -> int:
    movements = read_movements_from(path)
    waypoint_ew = 10
    waypoint_ns = 1
    offset_ew = 0
    offset_ns = 0
    for movement in movements:
        code, value = movement
        if code == 'N':
            waypoint_ns += value
        elif code == 'S':
            waypoint_ns -= value
        elif code == 'E':
            waypoint_ew += value
        elif code == 'W':
            waypoint_ew -= value
        elif code == 'L' or code == 'R':
            waypoint_ew, waypoint_ns = modify_waypoint(waypoint_ew, waypoint_ns, value, code)
        elif code == 'F':
            offset_ew += value * waypoint_ew
            offset_ns += value * waypoint_ns
        else:
            assert False
    return abs(offset_ns) + abs(offset_ew)


if __name__ == '__main__':
    print(count_manhattan_of_movements_2('d12_ship_input.txt'))
