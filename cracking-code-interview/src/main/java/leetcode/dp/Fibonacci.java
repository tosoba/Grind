package leetcode.dp;

import java.util.HashMap;

public class Fibonacci {
    private static final HashMap<Integer, Integer> memo = new HashMap<>();

    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        var result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(30));
    }
}
