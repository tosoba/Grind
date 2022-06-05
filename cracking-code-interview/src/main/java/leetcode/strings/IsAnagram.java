package leetcode.strings;

import java.util.HashMap;

public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || t.length() != s.length()) return false;

        var targets = new HashMap<Integer, Integer>();
        s.chars().forEach(c -> targets.merge(c, 1, Integer::sum));
        var counts = new HashMap<Integer, Integer>();
        for (var c : targets.keySet()) {
            counts.put(c, 0);
        }

        var total = 0;
        for (var i = 0; i < t.length(); ++i) {
            int c = t.charAt(i);
            if (targets.containsKey(c)) {
                if (counts.get(c) < targets.get(c)) ++total;
                counts.put(c, counts.get(c) + 1);
            }
        }
        return total == t.length();
    }

    public static void main(String[] args) {
        System.out.println(new IsAnagram().isAnagram("rat", "car"));
    }
}
