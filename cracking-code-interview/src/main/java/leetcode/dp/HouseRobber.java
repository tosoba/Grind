package leetcode.dp;

import java.util.HashMap;
import java.util.stream.IntStream;

public class HouseRobber {
    private final HashMap<Integer, Integer> memo = new HashMap<>();

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        memo.put(0, nums[0]);
        memo.put(1, Math.max(nums[0], nums[1]));
        return maxUpToInclusive(nums, nums.length - 1);
    }

    private int robHelper(int[] nums, int n) {
        if (memo.containsKey(n)) return memo.get(n);
        var result = maxUpToInclusive(nums, n - 2);
        result += nums[n];
        memo.put(n, result);
        return result;
    }

    private int maxUpToInclusive(int[] nums, int n) {
        return IntStream.rangeClosed(0, n).boxed().mapToInt(i -> robHelper(nums, i)).max().orElseThrow();
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.rob(new int[]{1, 3, 1, 3, 100}));
    }
}
