package leetcode.rand_arithmetic;

import java.util.Arrays;

public class KthLargest {
  public int findKthLargest(int[] nums, int k) {
    Arrays.sort(nums);
    for (int i = 0; i < Math.min(nums.length / 2, k); i++) {
      var temp = nums[i];
      nums[i] = nums[nums.length - 1 - i];
      nums[nums.length - 1 - i] = temp;
    }
    return nums[k - 1];
  }

  public static void main(String[] args) {
    System.out.println(new KthLargest().findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2));
    System.out.println(new KthLargest().findKthLargest(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
  }
}
