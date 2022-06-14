package leetcode.bit;

import java.util.Arrays;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);
    }

    public int singleNumber(int[] nums, int mask) {
        return Arrays.stream(nums).reduce(mask, (a, b) -> a ^ b);
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{2,2,3}));
    }
}
