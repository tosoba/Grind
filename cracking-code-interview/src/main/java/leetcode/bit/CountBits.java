package leetcode.bit;

import java.util.Arrays;

public class CountBits {
    private boolean getBit(int n, int i) {
        return (n & (1 << i)) != 0;
    }

    public int hammingWeight(int n) {
        int ans = 0;
        for (var i = 0; i < 32; ++i) {
            if (getBit(n, i)) ++ans;
        }
        return ans;
    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (var i = 0; i <= n; ++i) {
            ans[i] = hammingWeight(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountBits().countBits(5)));
    }
}
