package stack;

import java.util.Stack;

public class ValidParentheses {
  public boolean isValid(String s) {
    if (s.length() % 2 == 1) return false;
    var st = new Stack<Character>();
    for (var i = 0; i < s.length(); ++i) {
      var c = s.charAt(i);
      if (c == '{' || c == '[' || c == '(') {
        st.push(c);
      } else {
        if (st.isEmpty()) return false;
        var top = st.pop();
        if ((c == '}' && top != '{') || (c == ']' && top != '[') || (c == ')' && top != '(')) {
          return false;
        }
      }
    }
    return st.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(new ValidParentheses().isValid("((([[{}]]())))"));
  }
}
