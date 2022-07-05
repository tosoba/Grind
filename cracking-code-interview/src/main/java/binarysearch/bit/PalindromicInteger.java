package binarysearch.bit;

public class PalindromicInteger {
  public boolean solve(int num) {
    var s = String.valueOf(num);
    for (var i = 0; i < s.length() / 2; ++i) {
      if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
    }
    return true;
  }
}
