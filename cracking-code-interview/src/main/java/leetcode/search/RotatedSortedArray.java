package leetcode.search;

public class RotatedSortedArray {
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

  private int findMinIndex(int[] nums) {
    if (nums.length == 1) return 0;
    if (nums.length == 2) return nums[0] < nums[1] ? 0 : 1;

    int start = 0;
    int end = nums.length - 1;
    while (true) {
      int mid = (start + end) / 2;
      int prev = mid == 0 ? nums.length - 1 : mid - 1;
      if (nums[prev] > nums[mid]) {
        return mid;
      }
      int next = mid == nums.length - 1 ? 0 : mid + 1;
      if (nums[next] < nums[mid]) {
        return next;
      }

      if (nums[start] < nums[mid] && nums[mid] > nums[end]) {
        start = mid + 1;
      } else {
        end = mid;
      }
    }
  }

  public int search(int[] nums, int target) {
    if (nums.length == 1) return nums[0] == target ? 0 : -1;
    int minIndex = findMinIndex(nums);
    int start = 0;
    int end = nums.length - 1;
    while (start < end) {
      int mid = (start + end) / 2;
      int shifted = (mid + minIndex) % nums.length;
      if (nums[shifted] == target) return shifted;
      else if (nums[shifted] > target) end = mid;
      else start = mid + 1;
    }
    int shifted = (start + minIndex) % nums.length;
    return nums[shifted] == target ? shifted : -1;
  }

  public static void main(String[] args) {
    //    System.out.println(new MinRotatedSortedArray().findMin(new int[] {4, 5, 6, 7, 0, 1, 2}));
    //    System.out.println(new MinRotatedSortedArray().findMin(new int[] {3, 4, 5, 1, 2}));
    //    System.out.println(new MinRotatedSortedArray().findMin(new int[] {11, 13, 15, 17}));
    //    System.out.println(new MinRotatedSortedArray().findMin(new int[] {2, 3, 4, 5, 1}));

    System.out.println(new RotatedSortedArray().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));
    System.out.println(new RotatedSortedArray().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));
    System.out.println(new RotatedSortedArray().search(new int[] {1, 3}, 3));
    System.out.println(new RotatedSortedArray().search(new int[] {5, 1, 3}, 5));
  }
}
