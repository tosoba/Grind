package leetcode.rand_arithmetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TopKFrequent {
  public int[] topKFrequent(int[] nums, int k) {
    var counts = new HashMap<Integer, Integer>();
    for (var num : nums) {
      counts.merge(num, 1, Integer::sum);
    }
    return counts.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .limit(k)
        .mapToInt(Map.Entry::getKey)
        .toArray();
  }

  public static void main(String[] args) {
    System.out.println(
        Arrays.toString(new TopKFrequent().topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2)));
  }
}
