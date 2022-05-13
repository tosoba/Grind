package leetcode.dp;

import java.util.HashMap;

public class Tribonacci {
    private static final HashMap<Integer, Integer> memo = new HashMap<>();

    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        var result = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(tribonacci(37));
    }
}
