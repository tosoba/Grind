package leetcode.rand_arithmetic;

import java.util.HashSet;

public class LongestConsecutiveSubSequence {
  public int longestConsecutiveUsingRemove(int[] nums) {
    if (nums.length == 0 || nums.length == 1) return nums.length;

    var numsSet = new HashSet<Integer>();
    for (var num : nums) {
      numsSet.add(num);
    }

    int maxLen = 0;
    while (!numsSet.isEmpty()) {
      var num = numsSet.iterator().next();
      int min = num, max = num;
      numsSet.remove(num);
      while (numsSet.contains(max + 1)) {
        max++;
        numsSet.remove(max);
      }
      while (numsSet.contains(min - 1)) {
        min--;
        numsSet.remove(min);
      }
      var len = max - min + 1;
      if (len > maxLen) maxLen = len;
    }

    return maxLen;
  }

  public int longestConsecutive(int[] nums) {
    if (nums.length == 0 || nums.length == 1) return nums.length;

    var numsSet = new HashSet<Integer>();
    for (var num : nums) {
      numsSet.add(num);
    }

    int maxLen = 0;
    for (var num : nums) {
      if (numsSet.contains(num - 1)) continue;
      int max = num;
      while (numsSet.contains(max + 1)) {
        max++;
      }
      maxLen = Math.max(max - num + 1, maxLen);
    }

    return maxLen;
  }

  public static void main(String[] args) {
    System.out.println(
        new LongestConsecutiveSubSequence().longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    System.out.println(
        new LongestConsecutiveSubSequence()
            .longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
  }
}
