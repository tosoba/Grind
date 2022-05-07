package leetcode.rand_arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoSum {
  static class Solution {
    public static int[] twoSum(int[] nums, int target) {
      var indexes = new HashMap<Integer, List<Integer>>();
      for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
        indexes.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
      }
      for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
        var searchFor = target - nums[i];
        var foundIndexes = indexes.get(searchFor);
        if (foundIndexes == null || (searchFor == nums[i] && foundIndexes.size() == 1)) continue;
        int finalI = i;
        return new int[] {
          i, foundIndexes.stream().filter(j -> j != finalI).findFirst().orElseThrow(IllegalArgumentException::new)
        };
      }

      return new int[] {};
    }
  }

  public static void main(String[] args) {
    Solution.twoSum(new int[] {2, 7, 11, 15}, 9);
  }
}
