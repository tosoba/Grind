package leetcode.strings;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        var chars = new HashSet<Character>();
        var maxLen = 0;
        var start = 0;
        var len = 0;
        for (var i = 0; i < s.length(); ++i) {
            var c = s.charAt(i);
            for (var j = start; chars.contains(c) && j < i; ++j) {
                chars.remove(s.charAt(j));
                --len;
                start = j + 1;
            }
            chars.add(c);
            ++len;
            if (len > maxLen) maxLen = len;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("aabaab!bb"));
    }
}
