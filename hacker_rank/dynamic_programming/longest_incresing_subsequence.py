# https://www.hackerrank.com/challenges/longest-increasing-subsequent/problem
# O(nlg(n)): https://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/

from typing import List


def find_index_of_seq_to_append(element: int, subsequences: List[List[int]]) -> int:
    begin = 0
    end = len(subsequences) - 1
    mid = (begin + end) // 2
    index_of_sequence_to_append = -1
    while end - begin > 1:
        if subsequences[mid][-1] < element <= subsequences[mid + 1][-1]:
            index_of_sequence_to_append = mid
            break
        if element > subsequences[mid + 1][-1]:
            mid = (begin + end) // 2
            begin = mid
        elif subsequences[mid][-1] > element:
            mid = (begin + end) // 2
            end = mid
        else:
            assert False
    if index_of_sequence_to_append == -1:
        if subsequences[mid][-1] < element <= subsequences[mid + 1][-1]:
            return mid
        elif subsequences[begin][-1] < element <= subsequences[begin + 1][-1]:
            return begin
        else:
            assert False
    return index_of_sequence_to_append


def is_seq_gt_or_eq_to(seq1: List[int], seq2: List[int]) -> bool:
    if seq1[-1] > seq2[-1]:
        return True
    elif seq1[-1] < seq2[-1]:
        return False
    else:
        return len(seq1) >= len(seq2)


def is_seq_lt_or_eq_to(seq1: List[int], seq2: List[int]) -> bool:
    if seq1[-1] < seq2[-1]:
        return True
    elif seq1[-1] > seq2[-1]:
        return False
    else:
        return len(seq1) <= len(seq2)


def should_insert_seq_at(index: int, seq: List[int], subsequences: List[List[int]]) -> bool:
    return is_seq_gt_or_eq_to(seq, subsequences[index - 1]) and is_seq_lt_or_eq_to(seq, subsequences[index])


def insert_sequence(seq: List[int], subsequences: List[List[int]]):
    begin = 0
    end = len(subsequences) - 1
    mid = (begin + end) // 2
    while end - begin > 1:
        if should_insert_seq_at(mid, seq, subsequences):
            subsequences.insert(mid, seq)
            return
        elif is_seq_lt_or_eq_to(seq, subsequences[mid]):
            mid = (begin + end) // 2
            end = mid
        elif is_seq_gt_or_eq_to(seq, subsequences[mid]):
            mid = (begin + end) // 2
            begin = mid
        else:
            assert False
    if should_insert_seq_at(mid, seq, subsequences):
        subsequences.insert(mid, seq)
        return
    if should_insert_seq_at(end, seq, subsequences):
        subsequences.insert(end, seq)
        return
    assert False


def create_and_insert_subsequence(element: int, subsequences: List[List[int]]):
    if not subsequences:
        subsequences.append([element])
        return
    if element <= subsequences[0][-1]:
        subsequences.insert(0, [element])
        return
    if element > subsequences[-1][-1]:
        subsequences.append([*subsequences[-1], element])
        return

    seq = [*subsequences[find_index_of_seq_to_append(element, subsequences)], element]
    insert_sequence(seq, subsequences)


def longest_increasing_subsequence_nlgn(arr: List[int]) -> int:
    if not arr:
        return 0
    if len(arr) == 1:
        return 1

    longest_subsequences: List[List[int]] = []
    for element in arr:
        create_and_insert_subsequence(element, longest_subsequences)

    longest_sub_index = 0
    for index, subsequence in enumerate(longest_subsequences):
        if len(subsequence) > len(longest_subsequences[longest_sub_index]):
            longest_sub_index = index

    return len(longest_subsequences[longest_sub_index])


# O(n^2) - longest_subsequences list can be sorted by last element and then by sequence length (both asc) to achieve
# olg(n) by replacing inner for loop with binary search that would yield an index in longest_subsequence to insert a
# new (always new - otherwise sequences ending at previous elements would be lost) sequence ending at given element
def longest_increasing_subsequence_nsq(arr: List[int]) -> int:
    if not arr:
        return 0
    if len(arr) == 1:
        return 1

    longest_subs: List[List[int]] = []
    for element in arr:
        index_of_longest = -1
        for index, sub in enumerate(longest_subs):
            if element > sub[-1] and (index_of_longest == -1 or len(sub) + 1 > len(longest_subs[index_of_longest])):
                index_of_longest = index
        if index_of_longest != -1:
            longest_subs.append([*longest_subs[index_of_longest], element])
        else:
            longest_subs.append([element])

    index_of_longest = 0
    for index, sub in enumerate(longest_subs[1:], start=1):
        if len(sub) > len(longest_subs[index_of_longest]):
            index_of_longest = index

    return len(longest_subs[index_of_longest])


if __name__ == '__main__':
    # print(longest_increasing_subsequence_nsq([15, 27, 14, 38, 26, 55, 46, 65, 85]))
    # print(longest_increasing_subsequence_nlgn([15, 27, 14, 38, 26, 55, 46, 65, 85]))
    # print(longest_increasing_subsequence_nlgn([2, 4, 3, 7, 4, 5]))
    # print(longest_increasing_subsequence_nlgn([5, 2, 7, 4, 3, 8]))
    print(longest_increasing_subsequence_nsq([6, 2, 4, 3, 7, 4, 5]))
    pass
