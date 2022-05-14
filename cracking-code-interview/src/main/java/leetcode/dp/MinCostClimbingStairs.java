package leetcode.dp;

import java.util.HashMap;

public class MinCostClimbingStairs {
    private final HashMap<Integer, Integer> memo = new HashMap<>();

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0 || cost.length == 1) return 0;
        if (cost.length == 2) return Math.min(cost[0], cost[1]);
        memo.put(0, cost[0]);
        memo.put(1, cost[1]);
        return Math.min(minCostClimbingStairsHelper(cost, cost.length - 1),
                minCostClimbingStairsHelper(cost, cost.length - 2));
    }

    private int minCostClimbingStairsHelper(int[] cost, int n) {
        if (memo.containsKey(n)) return memo.get(n);
        var steps1 = memo.containsKey(n - 1) ? memo.get(n - 1) : minCostClimbingStairsHelper(cost, n - 1);
        var steps2 = memo.containsKey(n - 2) ? memo.get(n - 2) : minCostClimbingStairsHelper(cost, n - 2);
        var result = Math.min(steps1 + cost[n], steps2 + cost[n]);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MinCostClimbingStairs().minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }
}
