package leetcode.strings;

import java.util.HashMap;

// https://leetcode.com/problems/minimum-window-substring/submissions/
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        var targets = new HashMap<Integer, Integer>();
        t.chars().forEach(c -> targets.merge(c, 1, Integer::sum));
        var counts = new HashMap<Integer, Integer>();
        for (var c : targets.keySet()) {
            counts.put(c, 0);
        }

        var total = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int minStart = 0;

        for (var i = 0; i < s.length(); ++i) {
            int next = s.charAt(i);
            if (targets.containsKey(next)) {
                if (counts.get(next) < targets.get(next)) ++total;
                counts.put(next, counts.get(next) + 1);
            }

            // NARROWING WINDOW FROM THE LEFT -> faster than O(n^2) maybe O(n + m) or smth similar
            if (total == t.length()) {
                int j = start;
                while (true) {
                    int prev = s.charAt(j);
                    if (targets.containsKey(prev)) {
                        if (counts.get(prev) > targets.get(prev)) {
                            counts.put(prev, counts.get(prev) - 1);
                            ++j;
                        } else {
                            break;
                        }
                    } else {
                        ++j;
                    }
                }

                int length = i - j + 1;
                if (length < minLength) {
                    minLength = length;
                    minStart = j;
                }
                start = j;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }

    public static void main(String[] args) {
        System.out.println(new MinWindowSubstring().minWindow("cabwefgewcwaefgcf", "cae"));
    }
}
