package leetcode.dnc;

import util.TestUtils;

import java.util.Arrays;

public class MedianOf2SortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double med1 = medianWithin(nums1, 0, nums1.length);
        double med2 = medianWithin(nums2, 0, nums2.length);
        if (med1 == med2) return med1;

        return 0;
    }

    public static double medianWithin(int[] nums) {
        return medianWithin(nums, 0, nums.length);
    }

    public static double medianWithin(int[] nums, int startIn, int endEx) {
        assert endEx - startIn >= 0;
        if (endEx - startIn == 1) return nums[startIn];
        int mid = (endEx + startIn) / 2;
        if (nums.length % 2 == 1) {
            return nums[mid];
        } else {
            return (double) (nums[mid] + nums[mid - 1]) / 2.0;
        }
    }

    public static void main(String[] args) {
        testMedianWithinRand(100);
    }

    private static void testMedianWithinRand(int cases) {
        for (var i = 0; i < cases; ++i) {
            var nums = TestUtils.randomIntArray(25, 20);
            if (nums.length == 0) continue;
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            System.out.println("Median: " + medianWithin(nums, 0, nums.length));
        }
    }
}
