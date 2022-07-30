package leetcode.dp;

public class LongestPalindromicString {
  // - palindromes can have odd/even number of characters
  // - for odd number ones iterate through string and for each letter try to grow palindrome
  // outwards
  // - for event number ones iterate through string and for each letter pair try to grow palindrome
  // outwards

  public String longestPalindrome(String s) {
    if (s == null || s.isEmpty()) return s;
    if (s.chars().distinct().count() == s.length()) return s.substring(0, 1);

    String longest = "";
    for (int i = 1; i < s.length() - 1; ++i) {
      var palindrome = new StringBuilder(String.valueOf(s.charAt(i)));
      for (int j = 1; i - j >= 0 && i + j < s.length(); ++j) {
        var left = s.charAt(i - j);
        var right = s.charAt(i + j);
        if (left != right) break;
        palindrome.append(right);
        palindrome.insert(0, left);
      }
      if (palindrome.length() > longest.length()) longest = palindrome.toString();
    }

    for (int i = 1; i < s.length(); ++i) {
      var left = s.charAt(i - 1);
      var right = s.charAt(i);
      if (left != right) continue;
      var palindrome = new StringBuilder();
      palindrome.append(left);
      palindrome.append(right);
      for (int j = 1; i - j - 1 >= 0 && i + j < s.length(); ++j) {
        left = s.charAt(i - j - 1);
        right = s.charAt(i + j);
        if (left != right) break;
        palindrome.append(right);
        palindrome.insert(0, left);
      }
      if (palindrome.length() > longest.length()) longest = palindrome.toString();
    }

    return longest;
  }

  public static void main(String[] args) {
    System.out.println(new LongestPalindromicString().longestPalindrome("cbbd"));
  }
}
