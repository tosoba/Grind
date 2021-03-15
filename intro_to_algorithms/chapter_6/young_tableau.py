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
        self.__build()

    def print_2d(self):
        for i in range(0, self.__rows):
            for j in range(0, self.__columns):
                index_1d = self.__1d_index_of(i, j)
                print(self.__elements[index_1d], end='')
                print(',', end='')
            print()

    def validate(self) -> bool:
        for i in range(0, self.__elements_count):
            index_of_right = self.__1d_index_of_right(*self.__2d_index_of(i))
            if index_of_right is not None \
                    and self.__elements[index_of_right] is not None \
                    and self.__elements[i] > self.__elements[index_of_right]:
                return False
            index_of_bottom = self.__1d_index_of_bottom(*self.__2d_index_of(i))
            if index_of_bottom is not None \
                    and self.__elements[index_of_bottom] is not None \
                    and self.__elements[i] > self.__elements[index_of_bottom]:
                return False
        return True

    @property
    def pop_min(self) -> int:
        assert self.__elements_count > 0
        root = self.__elements[0]
        self.__elements[0] = self.__elements[self.__elements_count - 1]
        self.__elements[self.__elements_count - 1] = None
        self.__elements_count -= 1

        current_index = 0
        while True:
            index_of_right = self.__1d_index_of_right(*self.__2d_index_of(current_index))
            index_of_bottom = self.__1d_index_of_bottom(*self.__2d_index_of(current_index))
            if index_of_right is not None \
                    and self.__elements[index_of_right] is not None \
                    and self.__elements[index_of_right] < self.__elements[current_index]:
                current_index = index_of_right
            if index_of_bottom is not None \
                    and self.__elements[index_of_bottom] is not None \
                    and self.__elements[index_of_bottom] < self.__elements[current_index]:
                current_index = index_of_bottom
            if current_index == index_of_right or current_index == index_of_bottom:
                self.__rearrange_from(current_index, recurse=False)
            else:
                break
        return root

    def insert(self, value: int):
        assert self.__elements_count < self.__columns * self.__rows
        self.__elements[self.__elements_count] = value
        self.__elements_count += 1
        self.__rearrange_from(self.__elements_count - 1)

    def __rearrange_from(self, index: int, recurse: bool = True):
        assert index < self.__elements_count
        index_of_left = self.__1d_index_of_parent_left(*self.__2d_index_of(index))
        index_of_top = self.__1d_index_of_parent_top(*self.__2d_index_of(index))
        index_of_parent = index
        if index_of_left is not None \
                and self.__elements[index_of_left] is not None \
                and self.__elements[index_of_left] > self.__elements[index_of_parent]:
            index_of_parent = index_of_left
        if index_of_top is not None \
                and self.__elements[index_of_top] is not None \
                and self.__elements[index_of_top] > self.__elements[index_of_parent]:
            index_of_parent = index_of_top
        if index_of_parent != index:
            self.__elements[index], self.__elements[index_of_parent] \
                = self.__elements[index_of_parent], self.__elements[index]
            if recurse:
                self.__rearrange_from(index_of_parent)

    def __build(self):
        for i in range(0, self.__elements_count):
            self.__rearrange_from(i)

    def __2d_index_of(self, index: int) -> (int, int):
        return index // self.__rows, index % self.__rows

    def __1d_index_of(self, row: int, column: int) -> int:
        return row * self.__columns + column

    def __1d_index_of_parent_left(self, row: int, column: int) -> int:
        assert 0 <= row < self.__rows and 0 <= column < self.__columns
        column_of_left = column - 1
        return self.__1d_index_of(row, column_of_left) if column_of_left >= 0 else None

    def __1d_index_of_parent_top(self, row: int, column: int) -> int:
        assert 0 <= row < self.__rows and 0 <= column < self.__columns
        row_of_top = row - 1
        return self.__1d_index_of(row_of_top, column) if row_of_top >= 0 else None

    def __1d_index_of_right(self, row: int, column: int) -> Optional[int]:
        assert 0 <= row < self.__rows and 0 <= column < self.__columns
        column_of_right = column + 1
        return self.__1d_index_of(row, column_of_right) if column_of_right < self.__columns else None

    def __1d_index_of_bottom(self, row: int, column: int) -> Optional[int]:
        assert 0 <= row < self.__rows and 0 <= column < self.__columns
        row_of_bottom = row + 1
        return self.__1d_index_of(row_of_bottom, column) if row_of_bottom < self.__rows else None


if __name__ == '__main__':
    test_arr = [100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97]
    print(len(test_arr))
    yt = YoungTableau(test_arr, 5, 5)
    yt.print_2d()
    print(yt.validate())
    yt.insert(88)
    yt.print_2d()
    print(yt.validate())
