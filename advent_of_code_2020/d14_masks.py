import re
from typing import Dict, List

from util.files import stripped_input_lines_from


class Program:
    def __init__(self, path: str) -> None:
        self._memory: Dict[int, int] = {}

        mask_re = re.compile('mask = (.*)')
        mem_re = re.compile('mem\\[(.*)] = (.*)')
        mask: str = ''
        for line in stripped_input_lines_from(path):
            if not line:
                assert False

            mask_re_result = mask_re.fullmatch(line)
            if mask_re_result:
                mask = mask_re_result.groups()[0]
            else:
                mem_re_result = mem_re.fullmatch(line)
                if mem_re_result:
                    address, value = mem_re_result.groups()
                    if not mask:
                        assert False
                    value_36 = bin(int(value))[2:].zfill(36)
                    result: List[str] = []
                    for i, mask_bit in enumerate(mask):
                        if mask_bit == 'X':
                            result.append(value_36[i])
                        elif mask_bit == '0' or mask_bit == '1':
                            result.append(mask_bit)
                        else:
                            assert False
                    assert len(result) == 36
                    self._memory[int(address)] = int(''.join(result), base=2)

    def sum_memory(self) -> int:
        return sum(self._memory.values())

    def print_memory(self):
        print(self._memory)


def run_program(path: str):
    print(Program(path).sum_memory())


if __name__ == '__main__':
    run_program('d14_masks_input.txt')
    pass
