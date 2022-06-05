package leetcode.strings;

import java.util.HashMap;

// https://leetcode.com/problems/permutation-in-string/
public class PermutationsInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < s1.length()) return false;

        var targets = new HashMap<Integer, Integer>();
        s1.chars().forEach(c -> targets.merge(c, 1, Integer::sum));
        var counts = new HashMap<Integer, Integer>();
        for (var c : targets.keySet()) {
            counts.put(c, 0);
        }

        var total = 0;
        for (var i = 0; i < s1.length(); ++i) {
            int c = s2.charAt(i);
            if (targets.containsKey(c)) {
                if (counts.get(c) < targets.get(c)) ++total;
                counts.put(c, counts.get(c) + 1);
            }
        }

        if (total == s1.length()) return true;

        for (var i = s1.length(); i < s2.length(); ++i) {
            int next = s2.charAt(i);
            if (targets.containsKey(next)) {
                if (counts.get(next) < targets.get(next)) ++total;
                counts.put(next, counts.get(next) + 1);
            }

            int prev = s2.charAt(i - s1.length());
            if (targets.containsKey(prev)) {
                counts.put(prev, counts.get(prev) - 1);
                if (counts.get(prev) < targets.get(prev)) --total;
            }

            if (total == s1.length()) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PermutationsInString().checkInclusion("adc", "dcda"));
    }
}
