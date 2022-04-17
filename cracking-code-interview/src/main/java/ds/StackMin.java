package ds;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Random;
import java.util.Stack;

@Slf4j
public class StackMin<V extends Comparable<V>> {
  private Node<V> top;

  private final Stack<V> mins = new Stack<>();

  @Data
  private static class Node<V> {
    @NonNull private final V value;
    private final Node<V> next;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public V peek() {
    return top == null ? null : top.value;
  }

  public V pop() {
    if (top == null) return null;
    val value = top.value;
    if (!mins.empty() && mins.peek().equals(value)) mins.pop();
    top = top.next;
    return value;
  }

  public void push(@NonNull V value) {
    top = new Node<>(value, top);
    if (mins.empty() || mins.peek().compareTo(value) > 0) mins.push(value);
  }

  public V min() {
    return mins.empty() ? null : mins.peek();
  }

  public static void main(String[] args) {
    val stack = new StackMin<Integer>();
    for (var i = 0; i < 10; ++i) {
      val value = new Random().nextInt(25);
      stack.push(value);
      log.info("Pushed: " + value);
      log.info("Min = " + stack.min());
    }
    for (var i = 0; i < 10; ++i) {
      val value = stack.pop();
      log.info("Popped: " + value);
      log.info("Min = " + stack.min());
    }
  }
}
