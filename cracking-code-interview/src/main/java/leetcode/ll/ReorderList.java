package leetcode.ll;

import java.util.ArrayList;

public class ReorderList {
  // O(n) time and space - reducing space to O(1) would require:
  // 1) finding mid node
  // 2) reversing the list from mid to end
  // 3) combining 1st half of the list (head to mid) with the resulting reversed list (now end to mid)
  public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;

    var nodes = new ArrayList<ListNode>();
    var current = head;
    while (current != null) {
      nodes.add(current);
      current = current.next;
    }

    for (var i = 0; i < nodes.size() / 2; ++i) {
      if (current == null) {
        current = nodes.get(i);
      } else {
        current.next = nodes.get(i);
        current = current.next;
      }
      current.next = nodes.get(nodes.size() - i - 1);
      current = current.next;
    }
    if (current != null && nodes.size() % 2 != 0) {
      current.next = nodes.get(nodes.size() / 2);
      current = current.next;
    }
    if (current != null) current.next = null;
  }

  public static void main(String[] args) {
    new ReorderList()
        .reorderList(
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
  }

  private static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
