from typing import Optional


class Node:
    def __init__(self, value: int) -> None:
        self.__value = value
        self.__next: Optional['Node'] = None

    @property
    def value(self):
        return self.__value

    def attach(self, next_node: 'Node') -> 'Node':
        self.__next = next_node
        return next_node

    def print(self):
        node = self
        while node is not None:
            print(node.__value)
            node = node.__next

    def reverse(self) -> 'Node':
        head = self
        current_next = head.__next
        while current_next is not None:
            next_next = current_next.__next
            current_next.__next = head
            self.__next = next_next
            head = current_next
            current_next = next_next
        return head


if __name__ == '__main__':
    head = Node(1)
    tail = head.attach(Node(2)).attach(Node(3)).attach(Node(4)).attach(Node(5))
    head.print()
    head.reverse().print()
