package leetcode.rand_arithmetic;

import java.util.ArrayList;

public class MaxLenPositiveProduct {
    private static class SubArray {
        final int startIndex;
        final int endIndex;
        final boolean oddNumberOfNegatives;
        final int firstNegativeIndex;
        final int lastNegativeIndex;

        public SubArray(int startIndex, int endIndex, boolean oddNumberOfNegatives, int firstNegativeIndex, int lastNegativeIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.oddNumberOfNegatives = oddNumberOfNegatives;
            this.firstNegativeIndex = firstNegativeIndex;
            this.lastNegativeIndex = lastNegativeIndex;
        }
    }

    public static int getMaxLen(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        var subArrays = new ArrayList<SubArray>();
        var startIndex = 0;
        var oddNumberOfNegatives = false;
        var firstNegativeIndex = -1;
        var lastNeg = -1;
        for (var i = 0; i < nums.length; ++i) {
            var num = nums[i];
            if (num < 0) {
                oddNumberOfNegatives = !oddNumberOfNegatives;
                if (firstNegativeIndex == -1) firstNegativeIndex = i;
                lastNeg = i;
            } else if (num == 0) {
                subArrays.add(new SubArray(startIndex, i, oddNumberOfNegatives, firstNegativeIndex, lastNeg));
                oddNumberOfNegatives = false;
                startIndex = i + 1;
                firstNegativeIndex = -1;
                continue;
            }
            if (i == nums.length - 1) {
                subArrays.add(new SubArray(startIndex, nums.length, oddNumberOfNegatives, firstNegativeIndex, lastNeg));
            }
        }

        var maxLen = 0;
        for (int i = 0, subArrsSize = subArrays.size(); i < subArrsSize; i++) {
            var sa = subArrays.get(i);
            if (sa.startIndex == sa.endIndex) continue;
            var len = sa.endIndex - sa.startIndex;
            if (sa.oddNumberOfNegatives) {
                var diffNegStart = sa.firstNegativeIndex - sa.startIndex + 1;
                var diffNegEnd = sa.endIndex - sa.lastNegativeIndex;
                len = len - Math.min(diffNegStart, diffNegEnd);
            }
            if (len > maxLen) maxLen = len;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(getMaxLen(new int[]{5, -20, -20, -39, -5, 0, 0, 0, 36, -32, 0, -7, -10, -7, 21, 20, -12, -34, 26, 2}));
    }
}
