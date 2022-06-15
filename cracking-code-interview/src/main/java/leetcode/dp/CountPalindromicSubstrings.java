package leetcode.dp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountPalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s == null || s.isEmpty()) return 0;

        final ArrayList<List<Integer>> memo = new ArrayList<>(s.length());
        char[] charArray = s.toCharArray();
        for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
            char c = charArray[i];
            ArrayList<Integer> starts = new ArrayList<>();
            memo.add(starts);
            starts.add(i);

            if (i == 0) continue;

            if (charArray[i - 1] == c) starts.add(i - 1);
            var prevStarts = memo.get(i - 1);
            for (var start : prevStarts) {
                if (start > 0 && charArray[start - 1] == c) starts.add(start - 1);
            }
        }

        return (int) memo.stream().mapToLong(Collection::size).sum();
    }

    public static void main(String[] args) {
        System.out.println(new CountPalindromicSubstrings().countSubstrings("abc"));
    }
}
