package algorithms_and_interviews.divide_and_conquer;

import java.util.List;
import java.util.stream.Stream;

public class LargestContiguousSum {
    private static int largestSum(List<Integer> list) {
        if (list == null || list.isEmpty()) return 0;
        if (list.size() == 1) return list.get(0);
        int mid = list.size() / 2;
        int sumLeft = largestSum(list.subList(0, mid));
        int sumRight = largestSum(list.subList(mid, list.size()));
        int sumThroughMid = sumThroughMidpoint(list);
        return Stream.of(sumLeft, sumRight, sumThroughMid).max(Integer::compareTo).get();
    }

    private static int sumThroughMidpoint(List<Integer> list) {
        int mid = list.size() / 2;
        int leftSum = 0;
        int rightSum = 0;
        int currentSum = 0;
        int i = 1;
        while (mid - i >= 0) {
            currentSum += list.get(mid - i);
            if (currentSum > leftSum) leftSum = currentSum;
            ++i;
        }
        currentSum = 0;
        i = 1;
        while (mid + i < list.size()) {
            currentSum += list.get(mid + i);
            if (currentSum > rightSum) rightSum = currentSum;
            ++i;
        }
        return leftSum + list.get(mid) + rightSum;
    }

    public static void main(String[] args) {
        System.out.println(largestSum(List.of(-2, -5, 6, -2, -3, 1, 5, -6)));
    }
}
