package leetcode.dp;

import java.util.*;

public class LongestIncreasingSubsequence {
  // it's possible to make it consistently faster (O(log(n))) with less memory using binary search
  // by maintaining and array/arraylist where each element will be the smallest subsequence "tail"
  // and index of each element will correspond to the length of that subsequence
  // an array/arraylist like that will be sorted in ascending order

  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    if (nums.length == 1) return 1;

    var seqLastNums = new TreeMap<Integer, TreeSet<Integer>>();
    seqLastNums.put(1, new TreeSet<>(List.of(nums[0])));

    for (var i = 1; i < nums.length; ++i) {
      var maxLength = 0;
      for (var entry : seqLastNums.descendingMap().entrySet()) {
        if (entry.getValue().lower(nums[i]) != null) {
          maxLength = entry.getKey();
          break;
        }
      }

      var lastNums = seqLastNums.get(maxLength + 1);
      if (lastNums != null) {
        lastNums.add(nums[i]);
      } else {
        seqLastNums.put(maxLength + 1, new TreeSet<>(List.of(nums[i])));
      }
    }

    return seqLastNums.lastKey();
  }

  public static void main(String[] args) {
    System.out.println(
        new LongestIncreasingSubsequence().lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    System.out.println(
        new LongestIncreasingSubsequence().lengthOfLIS(new int[] {0, 1, 0, 3, 2, 3}));
    System.out.println(
        new LongestIncreasingSubsequence().lengthOfLIS(new int[] {7, 7, 7, 7, 7, 7, 7}));
  }
}
