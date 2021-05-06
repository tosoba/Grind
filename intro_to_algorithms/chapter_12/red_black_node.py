from enum import Enum

from intro_to_algorithms.chapter_12.binary_tree import Node


class Color(Enum):
    BLACK = 0
    RED = 1


class RedBlackNode(Node):
    def __init__(self, value: int, color: 'Color') -> None:
        super().__init__(value)
        self._color: 'Color' = color

    @property
    def color(self) -> 'Color':
        return self._color

    def __attach_rotated_child_to_parent(self, rotated_child: 'RedBlackNode'):
        if self.parent is None:
            return
        if self == self.parent.right:
            self.parent.right = rotated_child
        else:
            self.parent.left = rotated_child

    def left_rotate(self):
        rotated_child = self.right
        self.right = rotated_child.left
        self.__attach_rotated_child_to_parent(rotated_child)
        rotated_child.left = self

    def right_rotate(self):
        rotated_child = self.left
        self.left = rotated_child.right
        self.__attach_rotated_child_to_parent(rotated_child)
        rotated_child.right = self


if __name__ == '__main__':
    pass
