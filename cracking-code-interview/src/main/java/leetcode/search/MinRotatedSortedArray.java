package leetcode.search;

public class MinRotatedSortedArray {
  public int findMin(int[] nums) {
    if (nums.length == 1) return nums[0];
    if (nums.length == 2) return Math.min(nums[0], nums[1]);

    int start = 0;
    int end = nums.length - 1;
    while (true) {
      int mid = (start + end) / 2;
      int prev = mid == 0 ? nums.length - 1 : mid - 1;
      if (nums[prev] > nums[mid]) {
        return nums[mid];
      }
      int next = mid == nums.length - 1 ? 0 : mid + 1;
      if (nums[next] < nums[mid]) {
        return nums[next];
      }

      if (nums[start] < nums[mid] && nums[mid] > nums[end]) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(new MinRotatedSortedArray().findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
    System.out.println(new MinRotatedSortedArray().findMin(new int[] {3, 4, 5, 1, 2}));
    System.out.println(new MinRotatedSortedArray().findMin(new int[] {11, 13, 15, 17}));
    System.out.println(new MinRotatedSortedArray().findMin(new int[] {2, 3, 4, 5, 1}));
  }
}
