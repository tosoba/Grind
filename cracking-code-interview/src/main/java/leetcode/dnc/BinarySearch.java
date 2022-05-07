package leetcode.dnc;

import util.TestUtils;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
    static int indexOf(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return indexOf(nums, target, 0, nums.length - 1);
    }

    static int indexOf(int[] nums, int target, int start, int end) {
        if (start == end) {
            if (nums[start] == target) return start;
            else return -1;
        }

        int mid = (end + start) / 2;
        if (nums[mid] == target) return mid;
        if (target > nums[mid]) return indexOf(nums, target, mid + 1, end);
        else return indexOf(nums, target, start, mid);
    }

    public static void main(String[] args) {
        testWithRandom(1000);
    }

    private static void testWithRandom(int cases) {
        var rand = new Random();
        for (var i = 0; i < cases; ++i) {
            var nums = TestUtils.randomIntArray(25, 20);
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            var target = rand.nextInt(25);
            System.out.println("Target: " + target);
            System.out.println("Index of target: " + indexOf(nums, target));
        }
    }
}
