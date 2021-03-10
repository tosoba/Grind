from typing import List, Optional


class YoungTableau:
    def __init__(self, elements: List[int], rows: int, columns: int) -> None:
        assert elements and rows > 0 and columns > 0 and rows * columns >= len(elements)
        self.__rows = rows
        self.__columns = columns
        self.__elements: List[Optional[int]] = elements
        self.__elements_count = len(elements)
        for i in range(len(elements), rows * columns):
            self.__elements.append(None)

    @property
    def pop_min(self) -> int:
        root = self.__elements[0]
        self.__elements_count -= 1
        return root

    def __1d_index_of(self, row: int, column: int) -> int:
        return row * self.__columns + column

    def __1d_index_of_right(self, row: int, column: int) -> Optional[int]:
        assert row < self.__rows and column < self.__columns
        column_of_right = column + 1
        return self.__1d_index_of(row, column_of_right) if column_of_right < self.__columns else None

    def __1d_index_of_bottom(self, row: int, column: int) -> Optional[int]:
        assert row < self.__rows and column < self.__columns
        row_of_bottom = row + 1
        return self.__1d_index_of(row_of_bottom, column) if row_of_bottom < self.__rows else None
