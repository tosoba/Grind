package leetcode;

public class FizzBuzz {
  public static void main(String[] args) {
    for (var i = 1; i <= 100; ++i) {
      String s = "";
      if (i % 3 == 0) {
        s += "fizz";
      }
      if (i % 5 == 0) {
        s += "buzz";
      }
      if (s.isEmpty()) {
        s = String.valueOf(i);
      }
      System.out.println(s);
    }
  }
}
