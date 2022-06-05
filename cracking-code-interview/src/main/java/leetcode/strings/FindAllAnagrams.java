package leetcode.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAllAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || p.length() > s.length()) return Collections.emptyList();

        var starts = new ArrayList<Integer>();
        var targets = new HashMap<Integer, Integer>();
        p.chars().forEach(c -> targets.merge(c, 1, Integer::sum));
        var counts = new HashMap<Integer, Integer>();
        for (var c : targets.keySet()) {
            counts.put(c, 0);
        }

        var total = 0;
        for (var i = 0; i < p.length(); ++i) {
            int c = s.charAt(i);
            if (targets.containsKey(c)) {
                if (counts.get(c) < targets.get(c)) ++total;
                counts.put(c, counts.get(c) + 1);
            }
        }

        if (total == p.length()) {
            starts.add(0);
        }

        for (var i = p.length(); i < s.length(); ++i) {
            int next = s.charAt(i);
            if (targets.containsKey(next)) {
                if (counts.get(next) < targets.get(next)) ++total;
                counts.put(next, counts.get(next) + 1);
            }

            int prev = s.charAt(i - p.length());
            if (targets.containsKey(prev)) {
                counts.put(prev, counts.get(prev) - 1);
                if (counts.get(prev) < targets.get(prev)) --total;
            }

            if (total == p.length()) {
                starts.add(i - p.length() + 1);
            }
        }

        return starts;
    }

    public static void main(String[] args) {
        System.out.println(new FindAllAnagrams().findAnagrams("aa", "bb"));
    }
}
