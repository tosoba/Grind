package leetcode.rand_arithmetic;

import java.util.*;

public class SlidingWindowMax {
    public int[] maxSlidingWindowPQ(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        if (k > nums.length || k < 1) throw new IllegalArgumentException();

        var window = new PriorityQueue<Integer>(Comparator.reverseOrder());
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; ++i) {
            window.add(nums[i]);
        }

        for (int i = k; i < nums.length; ++i) {
            result[i - k] = window.peek();
            window.remove(nums[i - k]);
            window.add(nums[i]);
        }
        result[result.length - 1] = window.peek();

        return result;
    }

    public int[] maxSlidingWindowDEQ(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        if (k > nums.length || k < 1) throw new IllegalArgumentException();

        var window = new ArrayDeque<Integer>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; ++i) {
            while (window.peekLast() != null && nums[i] > window.peekLast()) {
                window.pollLast();
            }
            window.addLast(nums[i]);
        }

        for (int i = k; i < nums.length; ++i) {
            result[i - k] = nums[i - k] == window.peekFirst() ? window.pollFirst() : window.peekFirst();
            while (window.peekLast() != null && nums[i] > window.peekLast()) {
                window.pollLast();
            }
            window.addLast(nums[i]);
        }
        result[result.length - 1] = window.pollFirst();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SlidingWindowMax().maxSlidingWindowDEQ(new int[]{1, -1}, 1)));
    }
}
