package leetcode.rand_arithmetic;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            result[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        new RotateArray().rotate(nums, 3);
        System.out.println(Arrays.stream(nums).boxed().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
