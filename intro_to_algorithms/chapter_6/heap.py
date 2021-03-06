from typing import List


class MaxHeap:
    def __init__(self, elements: List[int]) -> None:
        self._elements = elements

    def _heapify(self, index: int):
        assert index < len(self._elements)
        index_of_left = MaxHeap._index_of_left(index)
        index_of_right = MaxHeap._index_of_right(index)
        index_of_largest = index
        if index_of_left < len(self._elements) and self._elements[index_of_left] > self._elements[index_of_largest]:
            index_of_largest = index_of_left
        if index_of_right < len(self._elements) and self._elements[index_of_right] > self._elements[index_of_largest]:
            index_of_largest = index_of_right

        if index_of_largest != index:
            self._elements[index], self._elements[index_of_largest] \
                = self._elements[index_of_largest], self._elements[index]
            self._heapify(index_of_largest)

    def build(self) -> 'MaxHeap':
        for i in range(len(self._elements) // 2, -1, -1):
            self._heapify(i)
        return self

    @property
    def non_mutating_sorted_elements_desc(self) -> List[int]:
        sorted_elements = []
        heap = self
        for i in range(len(self._elements), 0, -1):
            sorted_elements.append(heap._elements[0])
            if len(heap._elements) > 1:
                heap = heap._copy_without_root
        return sorted_elements

    @property
    def non_mutating_sorted_elements_asc(self) -> List[int]:
        return list(reversed(self.non_mutating_sorted_elements_desc))

    @property
    def _copy_without_root(self) -> 'MaxHeap':
        heap = MaxHeap(self._elements[1:])
        heap.build()
        return heap

    @staticmethod
    def _index_of_parent(i: int) -> int:
        return i // 2

    @staticmethod
    def _index_of_left(i: int) -> int:
        return 2 * i + 1

    @staticmethod
    def _index_of_right(i: int) -> int:
        return 2 * i + 2


if __name__ == '__main__':
    test_arr = [100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97]
    print(MaxHeap(test_arr).build().non_mutating_sorted_elements_asc)
    pass
