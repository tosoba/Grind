package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
  public List<List<Integer>> permute(int[] nums) {
    var perms = new ArrayList<List<Integer>>();
    generatePerms(nums, new ArrayList<>(), perms);
    return perms;
  }

  private void generatePerms(int[] nums, List<Integer> currentPerm, List<List<Integer>> perms) {
    if (currentPerm.size() >= nums.length) {
      perms.add(currentPerm);
      return;
    }

    for (var num : nums) {
      if (currentPerm.contains(num)) continue;
      var cp = new ArrayList<>(currentPerm);
      cp.add(num);
      generatePerms(nums, cp, perms);
    }
  }

  public static void main(String[] args) {
    System.out.println(new Permutations().permute(new int[] {1, 2, 3}));
  }
}
