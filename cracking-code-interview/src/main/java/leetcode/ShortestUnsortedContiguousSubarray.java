package leetcode;

public class ShortestUnsortedContiguousSubarray {
    public static void main(String[] args) {
        System.out.println(findUnsortedSubarray(new int[]{1, 3, 2, 3, 3}));
    }

    public static int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;

        int max = nums[0];
        int leftMostIndex = -1;
        for (int i = 1, numsLength = nums.length; i < numsLength; i++) {
            int num = nums[i];
            if (num < max) {
                leftMostIndex = i - 1;
                break;
            } else {
                max = num;
            }
        }

        if (leftMostIndex == -1) return 0;

        int rightMostIndex = -1;
        int min = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; --i) {
            int num = nums[i];
            if (num > min) {
                rightMostIndex = i + 1;
                break;
            } else {
                min = num;
            }
        }

        int minWithin = nums[leftMostIndex];
        int maxWithin = nums[leftMostIndex];
        for (int i = leftMostIndex + 1; i <= rightMostIndex; ++i) {
            int num = nums[i];
            if (num > maxWithin) {
                maxWithin = num;
            } else if (num < minWithin) {
                minWithin = num;
            }
        }

        int left = leftMostIndex;
        for (int i = leftMostIndex - 1; i >= 0; --i) {
            int num = nums[i];
            if (num <= minWithin) break;
            else left = i;
        }

        int right = rightMostIndex;
        for (int i = rightMostIndex + 1; i < nums.length; ++i) {
            int num = nums[i];
            if (num >= maxWithin) break;
            else right = i;
        }

        return right - left + 1;
    }
}
