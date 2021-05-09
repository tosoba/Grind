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

    def insert_iterative(self, node: 'RedBlackNode'):
        super().insert_iterative(node)
        node._color = Color.RED
        node.__insert_fix_up()

    def __insert_fix_up(self):
        current: 'RedBlackNode' = self
        while current.parent is not None and current.parent.color == Color.RED:
            if current.parent.is_left_child:
                uncle = current.parent.parent.right
                if uncle.color == Color.RED:
                    current.parent._color = Color.BLACK
                    uncle._color = Color.BLACK
                    current.parent.parent._color = Color.RED
                    current = current.parent.parent
                elif current.is_right_child:
                    current = current.parent
                    current.left_rotate()
                elif current.is_left_child:
                    current.parent._color = Color.BLACK
                    current.parent.parent._color = Color.RED
                    current.right_rotate()
                else:
                    assert False
            elif current.parent.is_right_child:
                uncle = current.parent.parent.left
                if uncle.color == Color.RED:
                    current.parent._color = Color.BLACK
                    uncle._color = Color.BLACK
                    current.parent.parent._color = Color.RED
                    current = current.parent.parent
                elif current.is_left_child:
                    current = current.parent
                    current.right_rotate()
                elif current.is_right_child:
                    current.parent._color = Color.BLACK
                    current.parent.parent._color = Color.RED
                    current.left_rotate()
                else:
                    assert False
            else:
                assert False

        current.root._color = Color.BLACK

    def replace_with(self, node: 'RedBlackNode') -> 'RedBlackNode':
        if self.is_left_child:
            self.parent.left = node
        elif self.is_right_child:
            self.parent.right = node
        self._parent = None
        node.left = self.left
        node.right = self.right
        return node

    def delete(self):
        current = self
        current_original_color = current.color
        node_to_fix: 'RedBlackNode'
        if self.left is None:
            node_to_fix = self.right
            self.replace_with(node_to_fix)
        elif self.right is None:
            node_to_fix = self.left
            self.replace_with(node_to_fix)
        else:
            current = self.right.min
            current_original_color = current.color
            node_to_fix = current.right
            if current.parent != self:
                current.replace_with(current.right)
                current.right = node_to_fix.right
            current._color = self.color
            self.replace_with(current)
        if current_original_color == Color.BLACK:
            pass  # fix-up


if __name__ == '__main__':
    pass
