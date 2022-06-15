package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class MaxProductSubArray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] memoEnd = new int[nums.length];
        int[] memoEndAbs = new int[nums.length];
        memoEnd[0] = nums[0];
        memoEndAbs[0] = nums[0];
        for (var i = 1; i < nums.length; ++i) {
            int productWithPrev = memoEnd[i - 1] * nums[i];
            int productWithPrevAbs = memoEndAbs[i - 1] * nums[i];
            memoEnd[i] = Math.max(productWithPrev, nums[i]);
            if (Math.abs(nums[i]) > Math.abs(productWithPrevAbs) && Math.abs(nums[i]) > memoEnd[i]) {
                memoEndAbs[i] = nums[i];
            } else {
                memoEndAbs[i] = Math.abs(productWithPrevAbs) > memoEnd[i] ? productWithPrevAbs : memoEnd[i];
            }
        }

        int[] memoStart = new int[nums.length];
        int[] memoStartAbs = new int[nums.length];
        memoStart[nums.length - 1] = nums[nums.length - 1];
        memoStartAbs[nums.length - 1] = nums[nums.length - 1];
        for (var i = nums.length - 2; i >= 0; --i) {
            int productWithNext = memoStart[i + 1] * nums[i];
            int productWithNextAbs = memoStartAbs[i + 1] * nums[i];
            memoStart[i] = Math.max(productWithNext, nums[i]);
            if (Math.abs(nums[i]) > Math.abs(productWithNextAbs) && Math.abs(nums[i]) > memoStart[i]) {
                memoStartAbs[i] = nums[i];
            } else {
                memoStartAbs[i] = Math.abs(productWithNextAbs) > memoStart[i] ? productWithNextAbs : memoStart[i];
            }
        }

        return Stream.of(Arrays.stream(memoEnd).max().orElseThrow(), Arrays.stream(memoEndAbs).max().orElseThrow(),
                Arrays.stream(memoStart).max().orElseThrow(), Arrays.stream(memoStartAbs).max().orElseThrow()).max(Comparator.naturalOrder()).orElseThrow();
    }

    public static void main(String[] args) {
        System.out.println(new MaxProductSubArray().maxProduct(new int[]{-1, -2, -3, 0}));
    }
}
