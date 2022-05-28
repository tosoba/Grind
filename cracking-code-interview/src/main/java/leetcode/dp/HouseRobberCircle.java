package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.IntStream;

public class HouseRobberCircle {
    private int[] memo;
    private int[] memoNoFirst;

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        if (nums.length == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        memo = new int[nums.length];
        memoNoFirst = new int[nums.length];
        Arrays.fill(memo, -1);
        Arrays.fill(memoNoFirst, -1);
        memo[0] = nums[0];
        memo[1] = nums[1];
        memoNoFirst[0] = 0;
        memoNoFirst[1] = nums[1];
        maxUpToInclusive(nums, nums.length - 1, true);
        maxUpToInclusive(nums, nums.length - 1, false);
        var max = 0;
        for (var i = 0; i < nums.length - 1; ++i) {
            if (memo[i] > max) max = memo[i];
        }
        return Math.max(max, memoNoFirst[nums.length - 1]);
    }

    private int robHelper(int[] nums, int n, boolean includeFirst) {
        var m = includeFirst ? memo : memoNoFirst;
        if (m[n] != -1) return m[n];
        var result = includeFirst || n - 2 > 0 ? maxUpToInclusive(nums, n - 2, includeFirst) : 0;
        result += nums[n];
        m[n] = result;
        return result;
    }

    private int maxUpToInclusive(int[] nums, int n, boolean includeFirst) {
        return IntStream.rangeClosed(includeFirst ? 0 : 1, n)
                .boxed()
                .mapToInt(i -> robHelper(nums, i, includeFirst))
                .max()
                .orElseThrow();
    }

    public static void main(String[] args) {
        HouseRobberCircle houseRobber = new HouseRobberCircle();
        System.out.println(houseRobber.rob(new int[]{100, 2, 3, 100}));
        int a = 4;
    }
}
