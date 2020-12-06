# https://adventofcode.com/2019/day/2

def run_program(program: str, noun: int = 0, verb: int = 0) -> int:
    program_arr = [int(el) for el in program.split(',')]
    program_arr[1] = noun
    program_arr[2] = verb

    op_code_index = 0
    while op_code_index < len(program_arr):
        op_code = program_arr[op_code_index]
        if op_code == 99:
            break
        elif op_code == 1:
            program_arr[program_arr[op_code_index + 3]] = program_arr[program_arr[op_code_index + 1]] + program_arr[
                program_arr[op_code_index + 2]]
        elif op_code == 2:
            program_arr[program_arr[op_code_index + 3]] = program_arr[program_arr[op_code_index + 1]] * program_arr[
                program_arr[op_code_index + 2]]
        else:
            assert False
        op_code_index += 4

    return program_arr[0]


def find_noun_verb_for_output(program: str, output: int = 19690720) -> (int, int):
    for noun in range(100):
        for verb in range(100):
            if run_program(program, noun, verb) == output:
                return noun, verb
    assert False


if __name__ == '__main__':
    n, v = find_noun_verb_for_output(
        '1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,9,1,19,1,19,5,23,1,9,23,27,2,27,6,31,1,5,31,35,2,9,35,39,2,6,39,43,2,43,'
        '13,47,2,13,47,51,1,10,51,55,1,9,55,59,1,6,59,63,2,63,9,67,1,67,6,71,1,71,13,75,1,6,75,79,1,9,79,83,2,9,83,'
        '87,1,87,6,91,1,91,13,95,2,6,95,99,1,10,99,103,2,103,9,107,1,6,107,111,1,10,111,115,2,6,115,119,1,5,119,123,'
        '1,123,13,127,1,127,5,131,1,6,131,135,2,135,13,139,1,139,2,143,1,143,10,0,99,2,0,14,0')
    print(100 * n + v)
