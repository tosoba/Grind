package leetcode.rand_arithmetic;

public class CanBeStrictlyIncreasing {
  // 1,2,5,5,7

  public boolean canBeIncreasing(int[] nums) {
    if (nums.length == 1) return true;
    var removed = false;
    for (var i = 0; i < nums.length - 1; ++i) {
      if (nums[i] < nums[i + 1]) continue;
      if (removed) return false;
      if (i == 0
          || i + 1 == nums.length - 1
          || nums[i - 1] < nums[i + 1]
          || nums[i] < nums[i + 2]) {
        removed = true;
      } else {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(
        new CanBeStrictlyIncreasing().canBeIncreasing(new int[] {105, 924, 32, 968}));
  }
}
