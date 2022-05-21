package leetcode.rand_arithmetic;

// 0,1,0,3,12

import java.util.Arrays;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int zerosAtTheEndCount = 0;
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] == 0) ++zerosAtTheEndCount;
            else break;
        }

        for (int i = 0; i < nums.length - 1 - zerosAtTheEndCount; ) {
            if (nums[i] != 0) {
                ++i;
                continue;
            }

            for (int j = i; j < nums.length - 1 - zerosAtTheEndCount; ++j) {
                nums[j] = nums[j + 1];
            }
            nums[nums.length - 1 - zerosAtTheEndCount] = 0;
            ++zerosAtTheEndCount;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        new MoveZeroes().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
