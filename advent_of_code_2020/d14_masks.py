import re
from typing import Dict, List

from util.files import stripped_input_lines_from


class Program1:
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


class Program2:
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

                    address_36 = bin(int(address))[2:].zfill(36)
                    result: List[str] = []
                    floating_indexes = []
                    for i, mask_bit in enumerate(mask):
                        if mask_bit == 'X':
                            result.append(mask_bit)
                            floating_indexes.append(i)
                        elif mask_bit == '1':
                            result.append(mask_bit)
                        elif mask_bit == '0':
                            result.append(address_36[i])
                        else:
                            assert False

                    addresses: List[int] = []
                    floating_bit_combos = [bin(num)[2:].zfill(len(floating_indexes)) for num in
                                           range(0, pow(2, len(floating_indexes)))]
                    for bit_combo in floating_bit_combos:
                        result_address = [bit for bit in result]
                        for i, floating_index in enumerate(floating_indexes):
                            result_address[floating_index] = bit_combo[i]
                        addresses.append(int(''.join(result_address), base=2))

                    for addr in addresses:
                        self._memory[addr] = int(value)

    def sum_memory(self) -> int:
        return sum(self._memory.values())

    def print_memory(self):
        print(self._memory)


if __name__ == '__main__':
    print(Program2('d14_masks_input.txt').sum_memory())
    pass
