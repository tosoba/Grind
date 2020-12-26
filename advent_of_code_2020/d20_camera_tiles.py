import re
from typing import Set, List, Dict

from util.files import stripped_input_lines_from


class Tile:
    def __init__(self, number: int, edges: List[str], content: List[str]) -> None:
        self._number = number
        self._edges = edges
        self._matching_tiles: Dict[str, int] = {edge: -1 for edge in edges}
        self._content = content

    @property
    def number(self) -> int:
        return self._number

    @property
    def edges(self) -> List[str]:
        return self._edges

    @property
    def matching_tiles(self) -> Dict[str, int]:
        return self._matching_tiles

    @property
    def content(self) -> List[str]:
        return self._content

    def check_matches_to(self, tile: 'Tile'):
        for edge in self.edges:
            reversed_edge = edge[::-1]
            if edge in tile.edges or reversed_edge in tile.edges:
                self._matching_tiles[edge] = tile.number

    def is_corner(self) -> bool:
        non_matching_edges = 0
        for matched_tile in self.matching_tiles.values():
            if matched_tile == -1:
                non_matching_edges += 1
        return non_matching_edges == 2


def read_corner_tiles_from(path: str):
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

    result = 1
    for tile in tiles:
        if tile.is_corner():
            result *= tile.number
    return result


def join_tiles(tiles: List[Tile]) -> List[str]:
    tiles_dict: Dict[int, Tile] = {tile.number: tile for tile in tiles}
    numbers_grid: List[List[int]] = [[tiles[0].number]]


if __name__ == '__main__':
    print(read_corner_tiles_from('d20_camera_tiles_input.txt'))
    pass
