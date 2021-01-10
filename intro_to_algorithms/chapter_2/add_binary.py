from typing import List


def add_binary(num1: List[bool], num2: List[bool]) -> List[bool]:
    result: List[bool] = []
    carry = False
    for i in range(1, max(len(num1), len(num2)) + 1):
        ones = 1 if carry else 0
        if len(num1) - i >= 0 and num1[len(num1) - 1]:
            ones += 1
        if len(num2) - i >= 0 and num2[len(num2) - 1]:
            ones += 1
        result.insert(0, ones % 2 != 0)
        carry = ones >= 2
    result.insert(0, carry)
    return result


if __name__ == '__main__':
    print(add_binary([1, 1], [1, 1, 1]))
