from typing import List


class ImmutableHeap:
    def __init__(self, elements: List[int], is_max: bool) -> None:
        self._elements = elements
        self._is_max = is_max

    def _heapify(self, index: int):
        assert index < len(self._elements)
        index_of_left = ImmutableHeap._index_of_left(index)
        index_of_right = ImmutableHeap._index_of_right(index)
        index_of_sub_root = index

        def should_swap_sub_root_min(child_index: int) -> bool:
            return self._elements[child_index] < self._elements[index_of_sub_root]

        def should_swap_sub_root_max(sub_root_index: int, child_index: int) -> bool:
            return self._elements[child_index] > self._elements[sub_root_index]

        should_swap_sub_root = should_swap_sub_root_max if self._is_max else should_swap_sub_root_min

        if index_of_left < len(self._elements) and should_swap_sub_root(index_of_left):
            index_of_sub_root = index_of_left
        if index_of_right < len(self._elements) and should_swap_sub_root(index_of_right):
            index_of_sub_root = index_of_right

        if index_of_sub_root != index:
            self._elements[index], self._elements[index_of_sub_root] \
                = self._elements[index_of_sub_root], self._elements[index]
            self._heapify(index_of_sub_root)

    def build(self) -> 'ImmutableHeap':
        for i in range(len(self._elements) // 2, -1, -1):
            self._heapify(i)
        return self

    @property
    def non_mutating_sorted_elements_top_to_bottom(self) -> List[int]:
        sorted_elements = []
        heap = self
        for i in range(len(self._elements), 0, -1):
            sorted_elements.append(heap._elements[0])
            if len(heap._elements) > 1:
                heap = heap._copy_without_root
        return sorted_elements

    @property
    def non_mutating_sorted_elements_bottom_to_top(self) -> List[int]:
        return list(reversed(self.non_mutating_sorted_elements_top_to_bottom))

    @property
    def _copy_without_root(self) -> 'ImmutableHeap':
        new_elements = self._elements[1:]
        new_elements.insert(0, new_elements[-1])
        new_elements.pop(-1)
        heap = ImmutableHeap(new_elements, self._is_max)
        heap._heapify(0)
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
    print(ImmutableHeap(test_arr, is_max=False).build().non_mutating_sorted_elements_top_to_bottom)
    pass
