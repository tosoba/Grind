from typing import List

from intro_to_algorithms.chapter_6.immutable_heap import ImmutableHeap


class PriorityQueue(ImmutableHeap):
    def __init__(self, elements: List[int], is_max: bool) -> None:
        super().__init__(elements, is_max)

    @property
    def max(self) -> int:
        assert self._is_max and self._elements
        return self._elements[0]

    @property
    def min(self) -> int:
        assert not self._is_max and self._elements
        return self._elements[0]

    @property
    def pop_max(self) -> int:
        assert self._is_max and self._elements
        return self.__pop_first

    @property
    def pop_min(self) -> int:
        assert not self._is_max and self._elements
        return self.__pop_first

    @property
    def __pop_first(self) -> int:
        root = self._elements.pop(0)
        self._elements.insert(0, self._elements.pop(-1))
        self._heapify_iterative(0)
        return root

    def mutate_element(self, index: int, value: int) -> 'PriorityQueue':
        assert index < len(self._elements)
        assert (value > self._elements[index] and self._is_max) or (value < self._elements[index] and not self._is_max)
        self._elements[index] = value
        self.__shift_element_at(index)
        return self

    def __shift_element_at(self, index: int):
        def should_swap_with_parent_max() -> bool:
            return self._elements[ImmutableHeap._index_of_parent(index)] < self._elements[index]

        def should_swap_with_parent_min() -> bool:
            return self._elements[ImmutableHeap._index_of_parent(index)] > self._elements[index]

        should_swap_with_parent = should_swap_with_parent_max if self._is_max else should_swap_with_parent_min

        while index > 0 and should_swap_with_parent():
            self._elements[index], self._elements[ImmutableHeap._index_of_parent(index)] \
                = self._elements[ImmutableHeap._index_of_parent(index)], self._elements[index]
            index = ImmutableHeap._index_of_parent(index)

    def insert(self, value: int) -> 'PriorityQueue':
        self._elements.append(value)
        self.__shift_element_at(len(self._elements) - 1)
        return self


if __name__ == '__main__':
    test_arr = [100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97]
    print(PriorityQueue(test_arr, is_max=False)
          .build()
          .insert(93)
          .mutate_element(7, 82)
          .non_mutating_sorted_elements_top_to_bottom)
