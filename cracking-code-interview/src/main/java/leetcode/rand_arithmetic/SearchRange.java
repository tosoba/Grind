package leetcode.rand_arithmetic;

public class SearchRange {
  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) return new int[] {-1, -1};
    int first = searchRangeBetween(nums, target, 0, nums.length);
    if (first == -1) return new int[] {-1, -1};

    int[] ans = new int[] {first, first};

    int left = first;
    do {
      left = first > 0 ? searchRangeBetween(nums, target, 0, left) : first;
      if (left >= 0) ans[0] = left;
    } while (left > 0 && left < first);

    int right = first;
    do {
      right =
          first < nums.length - 1
              ? searchRangeBetween(nums, target, right + 1, nums.length)
              : first;
      if (right >= 0) ans[1] = right;
    } while (right > 0 && right < nums.length - 1 && right > first);

    return ans;
  }

  private int searchRangeBetween(int[] nums, int target, int start, int end) {
    int index = -1;
    while (end - start > 1) {
      int mid = (start + end) / 2;
      if (nums[mid] > target) {
        end = mid;
      } else if (nums[mid] < target) {
        start = mid;
      } else {
        index = mid;
        break;
      }
    }
    if (index == -1 && nums[start] == target) index = start;
    return index;
  }

  public static void main(String[] args) {
    new SearchRange().searchRange(new int[] {1, 2, 2}, 2);
  }
}
