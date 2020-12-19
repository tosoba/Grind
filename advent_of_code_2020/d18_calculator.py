from util.files import stripped_input_lines_from


def evaluate_expression_without_parents(expression: str) -> int:
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


def evaluate_expression(expression: str) -> int:
    while expression.count('(') > 0:
        last_opening_parent_index = expression.rindex('(')
        closing_parent_index = expression.index(')', last_opening_parent_index)
        result = evaluate_expression_without_parents(
            expression[last_opening_parent_index + 1:closing_parent_index])
        expression = expression[:last_opening_parent_index] + str(result) + expression[closing_parent_index + 1:]
    return evaluate_expression_without_parents(expression)


def evaluate_expressions_from(path: str) -> int:
    result = 0
    expressions = stripped_input_lines_from(path)
    for expression in expressions:
        result += evaluate_expression(expression)
    return result


if __name__ == '__main__':
    print(evaluate_expressions_from('d18_calculator_input.txt'))
