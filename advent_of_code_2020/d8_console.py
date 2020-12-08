from typing import List, Set

from util.files import stripped_input_lines_from


class Instruction:
    def __init__(self, code: str, argument: int) -> None:
        self._code = code
        self._argument = argument

    @property
    def code(self) -> str:
        return self._code

    @code.setter
    def code(self, code):
        self._code = code

    @property
    def argument(self) -> int:
        return self._argument


def read_instructions_from(path: str) -> List[Instruction]:
    lines = stripped_input_lines_from(path)
    instructions = []
    for line in lines:
        code, argument = line.split(' ')
        instructions.append(Instruction(code, int(argument)))
    return instructions


def get_acc_before_inf_loop_start_from(path: str) -> int:
    instructions = read_instructions_from(path)
    acc = 0
    current_instruction_index = 0
    executed: Set[int] = set()
    while True:
        if current_instruction_index in executed:
            break
        executed.add(current_instruction_index)
        instruction = instructions[current_instruction_index]
        if instruction.code == 'nop':
            current_instruction_index += 1
        elif instruction.code == 'acc':
            acc += instruction.argument
            current_instruction_index += 1
        elif instruction.code == 'jmp':
            current_instruction_index += instruction.argument
        else:
            assert False
    return acc


def try_run_instructions(instructions: List[Instruction]) -> (int, bool):
    acc = 0
    current_instruction_index = 0
    executed: Set[int] = set()
    finished_successfully = True
    while current_instruction_index < len(instructions):
        if current_instruction_index in executed:
            finished_successfully = False
            break
        executed.add(current_instruction_index)
        instruction = instructions[current_instruction_index]
        if instruction.code == 'nop':
            current_instruction_index += 1
        elif instruction.code == 'acc':
            acc += instruction.argument
            current_instruction_index += 1
        elif instruction.code == 'jmp':
            current_instruction_index += instruction.argument
        else:
            assert False
    return acc, finished_successfully


def fix_program_from(path: str) -> int:
    instructions = read_instructions_from(path)
    for instruction in instructions:
        if instruction.code == 'nop':
            instruction.code = 'jmp'
            acc, finished_successfully = try_run_instructions(instructions)
            if finished_successfully:
                return acc
            else:
                instruction.code = 'nop'
        elif instruction.code == 'jmp':
            instruction.code = 'nop'
            acc, finished_successfully = try_run_instructions(instructions)
            if finished_successfully:
                return acc
            else:
                instruction.code = 'jmp'
    assert False


if __name__ == '__main__':
    print(fix_program_from('d8_console_input.txt'))
