package leetcode.dp;

public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return true;

        boolean[] memo = new boolean[nums.length];
        memo[nums.length - 1] = true;

        for (var i = nums.length - 2; i >= 0; --i) {
            fillMemo(nums, i, memo);
        }
        return memo[0];
    }

    private void fillMemo(int[] nums, int current, boolean[] memo) {
        var canReachLast = current + nums[current] >= nums.length - 1;
        if (canReachLast) {
            memo[current] = true;
            return;
        }

        for (var i = current + 1; i < memo.length && i <= current + nums[current]; ++i) {
            if (memo[i]) {
                memo[current] = true;
                return;
            }
        }
        memo[current] = false;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int[]{3, 2, 1, 0, 4}));
    }
}
