package leetcode.strings;

import java.util.*;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacementBF(String s, int k) {
        if (s == null || s.length() == 0) return 0;

        int maxLen = 1;
        Character prev = null;
        for (var i = 0; i < s.length(); ++i) {
            char current = s.charAt(i);
            if (prev != null && prev == current) continue;
            int len = 1;
            int changes = 0;
            for (var j = i + 1; j < s.length() && changes <= k; ++j) {
                if (current != s.charAt(j)) {
                    if (changes < k) ++changes;
                    else break;
                }
                ++len;
            }
            for (var j = i - 1; j >= 0 && changes <= k; --j) {
                if (current != s.charAt(j)) {
                    if (changes < k) ++changes;
                    else break;
                }
                ++len;
            }
            if (len > maxLen) maxLen = len;
            prev = current;
        }

        return maxLen;
    }

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;

        int maxLen = 0;
        var len = 0;
        var start = 0;
        var counts = new HashMap<Character, Integer>();
        var replacements = 0;
        char mostCommon = '*';
        var mostCommonCount = 0;

        for (var i = 0; i < s.length(); ++i) {
            var c = s.charAt(i);
            var count = counts.merge(c, 1, Integer::sum);
            if (c == mostCommon) ++mostCommonCount;
            else {
                if (count > mostCommonCount) {
                    mostCommon = c;
                    mostCommonCount = count;
                    replacements = i - start + 1 - mostCommonCount;
                } else {
                    ++replacements;
                }
            }

            for (var j = start; replacements > k && j < i; ++j) {
                var firstInWindow = s.charAt(j);
                counts.put(firstInWindow, counts.get(firstInWindow) - 1);
                if (firstInWindow == mostCommon) {
                    mostCommon = Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
                    mostCommonCount = counts.get(mostCommon);
                } else {
                    --replacements;
                }

                --len;
                ++start;
                replacements = i - start + 1 - mostCommonCount;
            }

            ++len;
            if (len > maxLen) maxLen = len;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new LongestRepeatingCharacterReplacement().characterReplacement("ABAA", 0));
    }
}
