package leetcode.stack;

import java.util.Stack;

public class ReversePolishNotation {
  public int evalRPN(String[] tokens) {
    var operands = new Stack<Integer>();
    for (var token : tokens) {
      if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
        var opn2 = operands.pop();
        var opn1 = operands.pop();
        int result = switch (token) {
          case "+" -> opn1 + opn2;
          case "-" -> opn1 - opn2;
          case "*" -> opn1 * opn2;
          case "/" -> opn1 / opn2;
          default -> throw new IllegalArgumentException();
        };
        operands.push(result);
      } else {
        operands.push(Integer.valueOf(token));
      }
    }
    return operands.peek();
  }

  public static void main(String[] args) {
    System.out.println(new ReversePolishNotation().evalRPN(new String[]{"2","1","+","3","*"}));
    System.out.println(new ReversePolishNotation().evalRPN(new String[]{"4","13","5","/","+"}));
    System.out.println(new ReversePolishNotation().evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
  }
}
