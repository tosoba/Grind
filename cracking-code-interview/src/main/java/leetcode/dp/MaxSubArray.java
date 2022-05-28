package leetcode.dp;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sums[i] = Math.max(sums[i - 1] + nums[i], nums[i]);
        }

        int max = sums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (max < sums[i]) max = sums[i];
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSubArray().maxSubArray(new int[]{5, 4, -1, 7, 8}));
    }
}
