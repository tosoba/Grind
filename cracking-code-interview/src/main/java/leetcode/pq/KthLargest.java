package leetcode.pq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargest {
  private final ArrayList<Integer> elements = new ArrayList<>();
  private final int k;

  public KthLargest(int k, int[] nums) {
    this.k = k;
    for (var num : nums) elements.add(num);
    Collections.sort(elements);
  }

  public int add(int val) {
    var index = Collections.binarySearch(elements, val);
    if (index < 0) {
      elements.add(-(index + 1), val);
    } else {
      elements.add(index, val);
    }
    return elements.get(elements.size() - k);
  }

  // no need to keep all elements in the inner data structure - keeping k elements suffices
  class KthLargestOptimized {
    private final int k;
    private final PriorityQueue<Integer> heap;

    public KthLargestOptimized(int k, int[] nums) {
      this.k = k;
      heap = new PriorityQueue<>();

      for (int num : nums) {
        heap.offer(num);
      }

      while (heap.size() > k) {
        heap.poll();
      }
    }

    public int add(int val) {
      heap.offer(val);
      if (heap.size() > k) {
        heap.poll();
      }

      return heap.peek();
    }
  }

  public static void main(String[] args) {
    var test = new KthLargest(3, new int[] {4, 5, 8, 2});
    test.add(3);
    test.add(5);
    test.add(10);
    test.add(9);
    test.add(4);
  }
}
