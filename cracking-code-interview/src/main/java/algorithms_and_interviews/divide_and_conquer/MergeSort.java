package algorithms_and_interviews.divide_and_conquer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class MergeSort {
    private static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        if (list == null) throw new IllegalArgumentException();
        if (list.isEmpty() || list.size() == 1) return list;
        int mid = list.size() / 2;
        return merge(mergeSort(list.subList(0, mid)), mergeSort(list.subList(mid, list.size())));
    }

    private static <T extends Comparable<T>> List<T> merge(List<T> list1, List<T> list2) {
        var result = new ArrayList<T>(list1.size() + list1.size());
        for (int i = 0, j = 0; i < list1.size() || j < list2.size(); ) {
            if (i == list1.size()) result.add(list2.get(j++));
            else if (j == list2.size()) result.add(list1.get(i++));
            else {
                if (list1.get(i).compareTo(list2.get(j)) <= 0) {
                    result.add(list1.get(i++));
                } else {
                    result.add(list2.get(j++));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var rand = new Random();
        System.out.println(mergeSort(Stream.generate(() -> rand.nextInt(100)).limit(10L).toList()));
    }
}
