package leetcode.dp;

public class LongestCommonSubsequence {
  public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) return 0;

    var memo = new int[1001][1001];
    for (var i = 0; i < text1.length(); ++i) {
      for (var j = 0; j < text2.length(); ++j) {
        memo[i + 1][j + 1] =
            text1.charAt(i) == text2.charAt(j)
                ? memo[i][j] + 1
                : Math.max(memo[i + 1][j], memo[i][j + 1]);
      }
    }

    return memo[text1.length()][text2.length()];
  }

  public static void main(String[] args) {
    // "bsbininm"
    // "jmjkbkjkv"

    // "abcba"
    // "abcbcba"
    System.out.println(
        new LongestCommonSubsequence().longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
    System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("abcba", "abcbcba"));
    System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("aaaaa", "aaaa"));
  }
}
