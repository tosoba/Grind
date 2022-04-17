package ds;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Random;
import java.util.Stack;

@Slf4j
public class StackPairQueue<V extends Comparable<V>> {
  private final Stack<V> stack1 = new Stack<>();
  private final Stack<V> stack2 = new Stack<>();

  public void add(V item) {
    if (stack1.empty()) {
      stack1.push(item);
      shift(stack2, stack1);
    } else if (stack2.empty()) {
      stack2.push(item);
      shift(stack1, stack2);
    } else {
      throw new IllegalStateException();
    }
  }

  private void shift(@NonNull Stack<V> from, @NonNull Stack<V> to) {
    if (from.empty()) return;
    val value = from.pop();
    if (!from.empty()) shift(from, to);
    to.push(value);
  }

  public V peek() {
    if (!stack1.empty()) {
      return stack1.peek();
    } else if (!stack2.empty()) {
      return stack2.peek();
    } else {
      return null;
    }
  }

  public V remove() {
    if (!stack1.empty()) {
      return stack1.pop();
    } else if (!stack2.empty()) {
      return stack2.pop();
    } else {
      return null;
    }
  }

  public static void main(String[] args) {
    val queue = new StackPairQueue<Integer>();
    for (var i = 0; i < 10; ++i) {
      val value = new Random().nextInt(25);
      queue.add(value);
      log.info("Added: " + value);
      log.info("1st = " + queue.peek());
    }
    for (var i = 0; i < 10; ++i) {
      val value = queue.remove();
      log.info("Removed: " + value);
      log.info("1st = " + queue.peek());
    }
  }
}
