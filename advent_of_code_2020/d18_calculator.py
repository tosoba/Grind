from typing import Callable

from util.files import stripped_input_lines_from


def evaluate_expression_without_parents_no_precedence(expression: str) -> int:
    current_index = 0
    total = int(expression[current_index:expression.index(' ', current_index)])
    while True:
        space_index = expression.index(' ', current_index)
        operation = expression[space_index + 1]
        last_expression = ' ' not in expression[space_index + 3:]
        operand = int(expression[space_index + 3:expression.index(' ', space_index + 3)]) \
            if not last_expression else int(expression[space_index + 3:])
        if operation == '+':
            total += operand
        elif operation == '*':
            total *= operand
        else:
            assert False
        if last_expression:
            break
        current_index = expression.index(' ', space_index + 3) - 1
    return total


def evaluate_expression_without_parents_with_precedence(expression: str) -> int:
    while '+' in expression:
        plus_index = expression.index('+')
        operand_1_first_index = expression[:plus_index - 1].rindex(' ') + 1 if ' ' in expression[:plus_index - 1] else 0
        operand_1 = int(expression[operand_1_first_index:plus_index - 1])
        last_expression = ' ' not in expression[plus_index + 2:]
        operand_2_last_index = expression.index(' ', plus_index + 2) if not last_expression else len(expression)
        operand_2 = int(expression[plus_index + 2:operand_2_last_index])
        result = operand_1 + operand_2
        expression = expression[:operand_1_first_index] + str(result) + expression[operand_2_last_index:]
    return evaluate_expression_without_parents_no_precedence(expression) if ' ' in expression else int(expression)


def evaluate_expression_no_precedence(expression: str) -> int:
    while '(' in expression:
        last_opening_parent_index = expression.rindex('(')
        closing_parent_index = expression.index(')', last_opening_parent_index)
        result = evaluate_expression_without_parents_no_precedence(
            expression[last_opening_parent_index + 1:closing_parent_index])
        expression = expression[:last_opening_parent_index] + str(result) + expression[closing_parent_index + 1:]
    return evaluate_expression_without_parents_no_precedence(expression)


def evaluate_expression_with_precedence(expression: str) -> int:
    while '(' in expression:
        last_opening_parent_index = expression.rindex('(')
        closing_parent_index = expression.index(')', last_opening_parent_index)
        result = evaluate_expression_without_parents_with_precedence(
            expression[last_opening_parent_index + 1:closing_parent_index])
        expression = expression[:last_opening_parent_index] + str(result) + expression[closing_parent_index + 1:]
    return evaluate_expression_without_parents_with_precedence(expression)


def evaluate_expressions_from(path: str, evaluator: Callable[[str], int]) -> int:
    result = 0
    for expression in stripped_input_lines_from(path):
        result += evaluator(expression)
    return result


if __name__ == '__main__':
    print(evaluate_expressions_from('d18_calculator_input.txt', evaluate_expression_with_precedence))
