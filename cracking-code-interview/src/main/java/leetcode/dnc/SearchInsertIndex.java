package leetcode.dnc;

public class SearchInsertIndex {
    static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        return indexOf(nums, target, 0, nums.length - 1);
    }

    static int indexOf(int[] nums, int target, int start, int end) {
        if (start == end) {
            if (nums[start] == target) return start;
            else if (start == nums.length - 1 && nums[nums.length - 1] < target) return start + 1;
            else return start;
        }

        int mid = (end + start) / 2;
        if (nums[mid] == target) return mid;
        if (target > nums[mid]) return indexOf(nums, target, mid + 1, end);
        else return indexOf(nums, target, start, mid);
    }
}
