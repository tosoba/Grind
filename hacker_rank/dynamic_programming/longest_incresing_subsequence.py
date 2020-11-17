# https://www.hackerrank.com/challenges/longest-increasing-subsequent/problem
# O(nlg(n)): https://www.geeksforgeeks.org/construction-of-longest-monotonically-increasing-subsequence-n-log-n/

from typing import List


# O(n^2)
def longest_increasing_subsequence(arr: List[int]) -> List[int]:
    if not arr:
        return []
    if len(arr) == 1:
        return arr

    longest_subsequences: List[List[int]] = [[arr[0]]]
    for element in arr:
        longest_sub_index = -1
        for j, subsequence in enumerate(longest_subsequences):
            if element > subsequence[-1] and len(subsequence) + 1 > len(longest_subsequences[longest_sub_index]):
                longest_sub_index = j
        if longest_sub_index != -1:
            longest_subsequences.append([*longest_subsequences[longest_sub_index], element])
        else:
            longest_subsequences.append([element])

    longest_sub_index = 0
    for i, subsequence in enumerate(longest_subsequences, start=1):
        if len(subsequence) > len(longest_subsequences[longest_sub_index]):
            longest_sub_index = i

    return longest_subsequences[longest_sub_index]


if __name__ == '__main__':
    print(longest_increasing_subsequence([15, 27, 14, 38, 26, 55, 46, 65, 85]))
