package leetcode.rand_arithmetic;

import java.util.HashMap;

public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || t.length() != s.length()) return false;

        var counts = new HashMap<Integer, Integer>();
        s.chars().forEach(c -> counts.merge(c, 1, Integer::sum));

        for (var i = 0; i < t.length(); ++i) {
            int c = t.charAt(i);
            if (!counts.containsKey(c)) return false;
            var count = counts.get(c);
            if (count == 0) return false;
            if (count - 1 > 0) {
                counts.put(c, count - 1);
            } else {
                counts.remove(c);
            }
        }

        return counts.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new IsAnagram().isAnagram("rat", "car"));
    }
}
