package leetcode.rand_arithmetic;

public class SearchRotatedArray {
  public int search(int[] nums, int target) {
    if (nums.length == 1) return nums[0] == target ? 0 : -1;
    int pivot = getRotatedBy(nums);
    return search(nums, target, pivot);
  }

  private int search(int[] nums, int target, int pivot) {
    int start = 0;
    int end = nums.length;
    int index = -1;
    while (end - start > 1) {
      int mid = (start + end) / 2;
      int pivoted = (mid + pivot) % nums.length;
      if (nums[pivoted] > target) {
        end = mid;
      } else if (nums[pivoted] < target) {
        start = mid;
      } else {
        index = mid;
        break;
      }
    }
    if (index == -1 && nums[(start + pivot) % nums.length] == target) index = start;
    return index == -1 ? -1 : (index + pivot) % nums.length;
  }

  private int getRotatedBy(int[] nums) {
    int start = 0, end = nums.length;
    if (nums[start] < nums[end - 1]) return 0;

    while (true) {
      int mid = (start + end) / 2;
      if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) return mid;
      if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) return mid + 1;

      if (nums[mid] > nums[end - 1]) {
        start = mid;
      } else if (nums[mid] < nums[start]) {
        end = mid;
      }
    }
  }

  public static void main(String[] args) {
    new SearchRotatedArray().search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3);
  }
}
