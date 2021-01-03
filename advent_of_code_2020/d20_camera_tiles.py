import re
from math import sqrt
from typing import Set, List, Dict, Tuple

from util.files import stripped_input_lines_from


class Tile:
    def __init__(self, number: int, edges: List[str], content: List[str]) -> None:
        self._number = number
        assert len(edges) == 4
        self._matching_tiles: Dict[str, Tuple[int, bool]] = {edge: (-1, False) for edge in edges}
        assert len(content) == 10
        self._content = content

    @property
    def number(self) -> int:
        return self._number

    @property
    def edges(self) -> List[str]:
        return [self.top, self.bottom, self.left, self.right]

    @property
    def top(self) -> str:
        return self.content[0]

    @property
    def bottom(self) -> str:
        return self.content[-1]

    @property
    def left(self) -> str:
        return ''.join([li[0] for li in self.content])

    @property
    def right(self) -> str:
        return ''.join([li[-1] for li in self.content])

    @property
    def matching_tiles(self) -> Dict[str, Tuple[int, bool]]:
        return self._matching_tiles

    @property
    def content(self) -> List[str]:
        return self._content

    def has_match(self, edge: str) -> bool:
        return self.matching_tiles[edge][0] != -1

    def check_matches_to(self, tile: 'Tile'):
        for edge in self.edges:
            if edge in tile.edges:
                self._matching_tiles[edge] = tile.number, False
            elif edge[::-1] in tile.edges:
                self._matching_tiles[edge] = tile.number, True

    def matching_edge_to(self, other_edge: str) -> Tuple[int, bool]:
        for index, edge in enumerate(self.edges):
            if edge == other_edge:
                return index, False
            elif edge[::-1] == other_edge:
                return index, True
        assert False

    def matching_edge_to_tile(self, tile: 'Tile') -> Tuple[int, bool]:
        for edge, value in self.matching_tiles.items():
            tile_number, reverse = value
            if tile.number == tile_number:
                edge_index, _ = self.matching_edge_to(edge)
                return edge_index, reverse
        assert False

    @property
    def is_corner(self) -> bool:
        return self.number_of_neighbours == 2

    @property
    def number_of_neighbours(self) -> int:
        matched_edges = 0
        for matched_tile, _ in self.matching_tiles.values():
            if matched_tile != -1:
                matched_edges += 1
        return matched_edges

    @property
    def neighbour_numbers(self) -> Set[int]:
        return {number for number, _ in self.matching_tiles.values()}


def read_tiles_from(path: str) -> List[Tile]:
    lines = stripped_input_lines_from(path)
    line_index = 0
    tile_number_re = re.compile('Tile (.*):')
    tile_numbers: Set[int] = set()
    tiles: List[Tile] = []
    while line_index < len(lines):
        tile_number_result = tile_number_re.fullmatch(lines[line_index])
        assert tile_number_result
        tile_number = int(tile_number_result.groups()[0])
        tile_numbers.add(tile_number)
        line_index += 1
        edges: List[str] = [lines[line_index],
                            lines[line_index + 9],
                            ''.join([li[0] for li in lines[line_index: line_index + 10]]),
                            ''.join([li[-1] for li in lines[line_index: line_index + 10]])]
        tiles.append(Tile(tile_number, edges, lines[line_index:line_index + 10]))
        line_index += 11

    for i in range(0, len(tiles)):
        for j in range(0, len(tiles)):
            if tiles[i].number == tiles[j].number:
                continue
            tiles[i].check_matches_to(tiles[j])

    return tiles


def read_corner_tiles_from(path: str):
    result = 1
    for tile in read_tiles_from(path):
        if tile.is_corner:
            result *= tile.number
    return result


def join_tiles_from(path: str):
    tiles: Dict[int, Tile] = {tile.number: tile for tile in read_tiles_from(path)}
    grid_side_len = sqrt(len(tiles))
    assert grid_side_len.is_integer()

    corners: List[int] = [tile.number for tile in tiles.values() if tile.is_corner]
    assert corners

    included_tile_numbers: Set[int] = set()
    side: List[Tile] = [tiles[corners[0]]]
    included_tile_numbers.add(corners[0])
    next_tile: Tile = tiles[[number for number, _ in tiles[corners[0]].matching_tiles.values() if number != -1][0]]
    included_tile_numbers.add(next_tile.number)
    side.append(next_tile)
    while len(side) < int(grid_side_len):
        next_tile = [tiles[number]
                     for number, _ in next_tile.matching_tiles.values()
                     if number != -1
                     and (tiles[number].number_of_neighbours == 3 or tiles[number].number_of_neighbours == 2)
                     and number not in included_tile_numbers][0]
        side.append(next_tile)
        included_tile_numbers.add(next_tile.number)
    assert len(side) == int(grid_side_len)
    tiles_grid: List[List[Tile]] = [side]

    while len(tiles_grid) < int(grid_side_len):
        neighbour_index = 1
        first_in_previous_row = tiles_grid[-1][0]
        next_tile_in_row = [tiles[number]
                            for number, _ in first_in_previous_row.matching_tiles.values()
                            if number != -1 and number not in included_tile_numbers][0]
        included_tile_numbers.add(next_tile_in_row.number)
        next_row: List[Tile] = [next_tile_in_row]
        while len(next_row) < int(grid_side_len):
            next_tile_in_row = [tiles[number]
                                for number, _ in next_tile_in_row.matching_tiles.values()
                                if number != -1
                                and number not in included_tile_numbers
                                and number in tiles_grid[-1][neighbour_index].neighbour_numbers][0]
            next_row.append(next_tile_in_row)
            included_tile_numbers.add(next_tile_in_row.number)
            neighbour_index += 1
        tiles_grid.append(next_row)

    for row in tiles_grid:
        for tile in row:
            print(tile.number, end=',')
        print()

    top_left = tiles_grid[0][0]
    first_neighbour = tiles_grid[0][1]
    top_left_tile_edge_index, _ = top_left.matching_edge_to_tile(first_neighbour)
    edge_to_match = top_left.edges[top_left_tile_edge_index]
    first_neighbour.matching_edge_to(edge_to_match)

    for row_index, row in enumerate(tiles_grid):
        for tile_index, tile in enumerate(row):
            if tile_index == 0:
                if row_index == 0:
                    continue
                else:
                    tile_to_match = tiles_grid[row_index - 1][0]
                    edge_to_match = tile_to_match.bottom
                    tile.matching_edge_to(edge_to_match)
            else:
                tile_to_match = tiles_grid[row_index][tile_index - 1]
                edge_to_match = tile_to_match.right
                tile.matching_edge_to(edge_to_match)


if __name__ == '__main__':
    join_tiles_from('d20_camera_tiles_input.txt')
    pass
