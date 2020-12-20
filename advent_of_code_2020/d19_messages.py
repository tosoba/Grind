from typing import List, Tuple, Dict

from util.files import stripped_input_lines_from


def read_rules_from(path: str) -> Tuple[Dict[int, str], Dict[int, List[List[int]]]]:
    starting_rules: Dict[int, str] = {}
    rules: Dict[int, List[List[int]]] = {}

    for line in stripped_input_lines_from(path):
        rule_number = int(line[:line.index(':')])
        if '"' in line:
            starting_rules[rule_number] = line[line.index('"') + 1]
        else:
            rules[rule_number] = []
            for alternatives in line[line.index(':') + 2:].split(' | '):
                rules_list = []
                for rule in alternatives.strip().split(' '):
                    rules_list.append(int(rule))
                rules[rule_number].append(rules_list)

    relay_rules: Dict[int, int] = {}
    for index, alternatives in rules.items():
        if len(alternatives) == 1 and len(alternatives[0]) == 1:
            relay_rules[index] = alternatives[0][0]
    for alternatives in rules.values():
        for alternative in alternatives:
            for i, rule in enumerate(alternative):
                if rule in relay_rules.keys():
                    alternative[i] = relay_rules[rule]
    for index in relay_rules.keys():
        rules.pop(index)

    return starting_rules, rules


def find_first_rule(message: str, starting_rules: Dict[int, str]) -> int:
    first_rule = -1
    for rule, char in starting_rules.items():
        if char == message[0]:
            first_rule = rule
    assert first_rule != -1
    return first_rule


def matches_rule(message: str,
                 rule_to_match: int,
                 rules: Dict[int, List[List[int]]],
                 starting_rules: Dict[int, str]) -> Tuple[bool, int]:
    assert message and rules and starting_rules

    if rule_to_match in starting_rules.keys():
        matched = message[0] == starting_rules[rule_to_match]
        return (matched, 1) if matched else (matched, 0)

    target_rule_alternatives = rules[rule_to_match]
    for alternative in target_rule_alternatives:
        matched = False
        substring_index = 0
        for rule in alternative:
            if substring_index >= len(message):
                return False, 0
            first_rule = find_first_rule(message[substring_index:], starting_rules)
            if rule == first_rule:
                matched = True
                substring_index += 1
            else:
                matched, substring_index_inc = matches_rule(message[substring_index:], rule, rules, starting_rules)
                substring_index += substring_index_inc
            if not matched:
                break

        if matched:
            return True, substring_index

    return False, 0


def matches_check_len(message: str,
                      rule_to_match: int,
                      rules: Dict[int, List[List[int]]],
                      starting_rules: Dict[int, str]) -> bool:
    matches, matched_len = matches_rule(message, rule_to_match, rules, starting_rules)
    return matches and len(message) == matched_len


def count_valid_messages(messages_path: str, rules_path: str) -> int:
    valid_count = 0
    starting_rules, rules = read_rules_from(rules_path)
    for line in stripped_input_lines_from(messages_path):
        if matches_check_len(line, 0, rules, starting_rules):
            valid_count += 1
    return valid_count


if __name__ == '__main__':
    print(count_valid_messages('d19_messages_input.txt', 'd19_rules_input.txt'))
    pass
