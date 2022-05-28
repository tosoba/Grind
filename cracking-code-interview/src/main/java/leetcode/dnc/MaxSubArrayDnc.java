package leetcode.dnc;

import java.util.stream.Stream;

public class MaxSubArrayDnc { // same as LargestContiguousSum
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return largestSum(nums, 0, nums.length);
    }

    private int largestSum(int[] nums, int start, int end) {
        if (end - start == 0) return 0;
        if (end - start == 1) return nums[start];
        int mid = (start + end) / 2;
        int sumLeft = largestSum(nums, start, mid);
        int sumRight = largestSum(nums, mid, end);
        int sumThroughMid = sumThroughMidpoint(nums, start, end);
        return Stream.of(sumLeft, sumRight, sumThroughMid).max(Integer::compareTo).get();
    }

    private int sumThroughMidpoint(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        int leftSum = 0;
        int rightSum = 0;
        int currentSum = 0;
        int i = 1;
        while (mid - i >= start) {
            currentSum += nums[mid - i];
            if (currentSum > leftSum) leftSum = currentSum;
            ++i;
        }

        currentSum = 0;
        i = 1;
        while (mid + i < end) {
            currentSum += nums[mid + i];
            if (currentSum > rightSum) rightSum = currentSum;
            ++i;
        }

        return leftSum + nums[mid] + rightSum;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSubArrayDnc().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
