package leetcode.dp;

import java.util.Arrays;
import java.util.Objects;

public class JumpGameMin {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;

        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        memo[nums.length - 1] = 0;

        for (var i = nums.length - 2; i >= 0; --i) {
            fillMemo(nums, i, memo);
        }
        return memo[0];
    }

    private void fillMemo(int[] nums, int current, int[] memo) {
        var canReachLast = current + nums[current] >= nums.length - 1;
        if (canReachLast) {
            memo[current] = 1;
            return;
        }

        Integer min = null;
        for (var i = current + 1; i < memo.length && i <= current + nums[current]; ++i) {
            if (memo[i] > -1 && (min == null || min > memo[i] + 1)) {
                min = memo[i] + 1;
            }
        }
        memo[current] = Objects.requireNonNullElse(min, -1);
    }

    public static void main(String[] args) {
        System.out.println(new JumpGameMin().jump(new int[]{2,3,1,1,4}));
    }
}
