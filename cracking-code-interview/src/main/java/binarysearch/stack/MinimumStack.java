package binarysearch.stack;

import java.util.Stack;

public class MinimumStack {
  private Stack<Integer> stack;
  private Stack<Integer> mins;

  public MinimumStack() {
    stack = new Stack<>();
    mins = new Stack<>();
  }

  public void append(int val) {
    stack.push(val);
    if (mins.isEmpty() || mins.peek() >= val) mins.push(val);
  }

  public int peek() {
    return stack.peek();
  }

  public int min() {
    return mins.peek();
  }

  public int pop() {
    var val = stack.pop();
    if (val.equals(mins.peek())) mins.pop();
    return val;
  }
}
