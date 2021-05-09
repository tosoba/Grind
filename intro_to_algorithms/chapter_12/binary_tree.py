from typing import Optional


class Node:
    def __init__(self, value: int) -> None:
        self._value: int = value
        self._left: Optional['Node'] = None
        self._right: Optional['Node'] = None
        self._parent: Optional['Node'] = None

    @property
    def left(self) -> 'Node':
        return self._left

    @property
    def right(self) -> 'Node':
        return self._right

    @property
    def parent(self) -> 'Node':
        return self._parent

    @property
    def value(self) -> int:
        return self._value

    @left.setter
    def left(self, left: 'Node'):
        self._left = left
        if left is not None:
            left._parent = self

    @right.setter
    def right(self, right: 'Node'):
        self._right = right
        if right is not None:
            right._parent = self

    def __str__(self) -> str:
        return 'Node: ' + str(self._value)

    def print_in_order(self):
        if self.left is not None:
            self.left.print_in_order()
        print(self)
        if self.right is not None:
            self.right.print_in_order()

    def search_recursive(self, value: int) -> Optional['Node']:
        if value == self.value:
            return self
        elif value > self.value:
            return None if self.right is None else self.right.search_recursive(value)
        else:
            return None if self.left is None else self.left.search_recursive(value)

    def search_iterative(self, value: int) -> Optional['Node']:
        current = self
        while current is not None and current.value != value:
            current = current.right if value > current.value else current.left
        return current

    @property
    def min(self) -> 'Node':
        current = self
        while current is not None:
            current = current.left
        return current

    @property
    def max(self) -> 'Node':
        current = self
        while current is not None:
            current = current.right
        return current

    @property
    def successor(self) -> Optional['Node']:
        if self.right is not None:
            return self.right.min
        current = self
        current_parent = current.parent
        while current_parent is not None and current_parent.right == current:
            current = current_parent
            current_parent = current.parent
        return current_parent

    @property
    def predecessor(self) -> Optional['Node']:
        if self.left is not None:
            return self.left.max
        current = self
        current_parent = current.parent
        while current_parent is not None and current_parent.left == current:
            current = current_parent
            current_parent = current.parent
        return current_parent

    @property
    def root(self) -> 'Node':
        current = self
        while current.parent is not None:
            current = current.parent
        return current

    def insert_iterative(self, node: 'Node'):
        current = self.root
        current_parent = current.parent
        while current is not None:
            current_parent = current
            current = current.left if node.value < current.value else current.right
        if current_parent.value > node.value:
            current_parent.left = node
        else:
            current_parent.right = node

    def insert_recursive(self, node: 'Node'):
        current = self.root
        parent = current.parent
        Node.__insert_recursive(current, parent, node)

    @staticmethod
    def __insert_recursive(current: 'Node', parent: 'Node', node: 'Node'):
        if current is None:
            if parent.value > node.value:
                parent.left = node
            else:
                parent.right = node
            return
        Node.__insert_recursive(current=current.left if node.value < current.value else current.right,
                                parent=current,
                                node=node)

    def delete(self):
        parent = self.parent
        children_count = 0
        if self.right is not None:
            children_count += 1
        if self.left is not None:
            children_count += 1

        if children_count == 0:
            if parent is None:
                return
            if self == parent.left:
                parent.left = None
            else:
                parent.right = None
        elif children_count == 1:
            if parent is None:
                return
            child = self.left if self.left is not None else self.right
            if self == parent.left:
                parent.left = child
            else:
                parent.right = child
        else:
            successor = self.successor
            if successor == self.right:
                successor.left = self.left
            else:
                if successor.right is not None:
                    self.right.left = successor.right
                successor.right = self.right
                successor.left = self.left
            if parent is None:
                return
            if self == parent.left:
                parent.left = successor
            else:
                parent.right = successor

    @property
    def is_left_child(self) -> bool:
        return self.parent is not None and self.parent.left == self

    @property
    def is_right_child(self) -> bool:
        return self.parent is not None and self.parent.right == self

    @property
    def is_attached(self) -> bool:
        return self.parent is not None or self.left is not None or self.right is not None


if __name__ == '__main__':
    root = Node(5)
    root.left = Node(4)
    to_delete = Node(6)
    root.right = to_delete
    root.insert_recursive(Node(3))
    root.insert_recursive(Node(4))
    root.insert_recursive(Node(9))
    to_delete.delete()
    root.print_in_order()
