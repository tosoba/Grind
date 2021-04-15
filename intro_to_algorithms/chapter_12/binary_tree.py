from typing import Optional


class Node:
    def __init__(self, value: int) -> None:
        self.__value: int = value
        self.__left: Optional['Node'] = None
        self.__right: Optional['Node'] = None
        self.__parent: Optional['Node'] = None

    @property
    def left(self) -> 'Node':
        return self.__left

    @property
    def right(self) -> 'Node':
        return self.__right

    @property
    def parent(self) -> 'Node':
        return self.__parent

    @property
    def value(self) -> int:
        return self.__value

    @left.setter
    def left(self, left: 'Node'):
        self.__left = left
        left.__parent = self

    @right.setter
    def right(self, right: 'Node'):
        self.__right = right
        right.__parent = self

    def __str__(self) -> str:
        return 'Node: ' + str(self.__value)

    def print_in_order(self):
        if self.__left is not None:
            self.__left.print_in_order()
        print(self)
        if self.__right is not None:
            self.__right.print_in_order()

    def search_recursive(self, value: int) -> Optional['Node']:
        if value == self.__value:
            return self
        elif value > self.__value:
            return None if self.__right is None else self.__right.search_recursive(value)
        else:
            return None if self.__left is None else self.__left.search_recursive(value)

    def search_iterative(self, value: int) -> Optional['Node']:
        current = self
        while current is not None and current.__value != value:
            current = current.__right if value > current.__value else current.__left
        return current

    @property
    def min(self) -> 'Node':
        current = self
        while current is not None:
            current = current.__left
        return current

    @property
    def max(self) -> 'Node':
        current = self
        while current is not None:
            current = current.__right
        return current

    @property
    def successor(self) -> Optional['Node']:
        if self.__right is not None:
            return self.__right.min
        current = self
        current_parent = current.__parent
        while current_parent is not None and current_parent.__right == current:
            current = current_parent
            current_parent = current.__parent
        return current_parent

    @property
    def predecessor(self) -> Optional['Node']:
        if self.__left is not None:
            return self.__left.max
        current = self
        current_parent = current.__parent
        while current_parent is not None and current_parent.__left == current:
            current = current_parent
            current_parent = current.__parent
        return current_parent

    @property
    def root(self) -> 'Node':
        current = self
        while current.__parent is not None:
            current = current.__parent
        return current

    def insert(self, value: int):
        current = self.root
        current_parent = current.__parent
        while current is not None:
            current_parent = current
            current = current.__left if value < current.value else current.__right
        if current_parent.__value > value:
            current_parent.left = Node(value)
        else:
            current_parent.right = Node(value)

    def delete(self):
        parent = self.__parent
        children_count = 0
        if self.__right is not None:
            children_count += 1
        if self.__left is not None:
            children_count += 1

        if children_count == 0:
            if parent is None:
                return
            if self == parent.__left:
                parent.__left = None
            else:
                parent.__right = None
        elif children_count == 1:
            if parent is None:
                return
            child = self.__left if self.__left is not None else self.__right
            if self == parent.__left:
                parent.left = child
            else:
                parent.right = child
        else:
            successor = self.successor
            if successor == self.__right:
                successor.left = self.__left
            else:
                if successor.__right is not None:
                    self.__right.left = successor.__right
                successor.right = self.__right
                successor.left = self.__left
            if parent is None:
                return
            if self == parent.__left:
                parent.left = successor
            else:
                parent.right = successor


if __name__ == '__main__':
    root = Node(5)
    root.left = Node(4)
    to_delete = Node(6)
    root.right = to_delete
    root.insert(3)
    root.insert(4)
    root.insert(9)
    to_delete.delete()
    root.print_in_order()
