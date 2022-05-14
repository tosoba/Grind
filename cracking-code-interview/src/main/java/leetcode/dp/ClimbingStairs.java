package leetcode.dp;

import java.util.HashMap;

public class ClimbingStairs {
    private static final HashMap<Integer, Integer> memo = new HashMap<>();

    static {
        memo.put(1, 1);
        memo.put(2, 2);
    }

    public int climbStairs(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        var steps1 = memo.containsKey(n - 1) ? memo.get(n - 1) : climbStairs(n - 1);
        var steps2 = memo.containsKey(n - 2) ? memo.get(n - 2) : climbStairs(n - 2);
        memo.put(n, steps1 + steps2);
        return steps1 + steps2;
    }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(3));
    }
}
