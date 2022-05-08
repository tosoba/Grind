package leetcode.dnc;

import util.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class MedianOf2SortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) return 0.0;
        if (nums1.length == 0) return medianWithin(nums2);
        if (nums2.length == 0) return medianWithin(nums1);

        int start1 = 0;
        int end1 = nums1.length;
        int start2 = 0;
        int end2 = nums2.length;

        while (end1 - start1 + end2 - start2 > 4) {
            double med1 = medianWithin(nums1, start1, end1);
            double med2 = medianWithin(nums2, start2, end2);
            if (med1 == med2) return med1;

            if (med1 > med2) {
                start2 = (start2 + end2) / 2;
                end1 = (start1 + end1) / 2 + 1;
            } else {
                start1 = (start1 + end1) / 2;
                end2 = (start2 + end2) / 2 + 1;
            }
        }

        var combined = new ArrayList<Integer>(end1 - start1 + end2 - start2);
        for (var i = start1; i < end1; ++i) combined.add(nums1[i]);
        for (var i = start2; i < end2; ++i) combined.add(nums2[i]);
        int[] combinedArr = combined.stream().mapToInt(i -> i).toArray();
        Arrays.sort(combinedArr);
        return medianWithin(combinedArr);
    }

    public static double medianWithin(int[] nums) {
        return medianWithin(nums, 0, nums.length);
    }

    public static double medianWithin(int[] nums, int startIn, int endEx) {
        assert endEx - startIn > 0;
        if (endEx - startIn == 1) return nums[startIn];
        int mid = (endEx + startIn) / 2;
        if (nums.length % 2 == 1) {
            return nums[mid];
        } else {
            return (double) (nums[mid] + nums[mid - 1]) / 2.0;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4, 5}));
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
