package leetcode.ll;

public class RemoveNthFromEnd {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || head.next == null) return null;

            int count = 1;
            ListNode it = head.next;
            while (it != null) {
                it = it.next;
                ++count;
            }

            if (n == count) return head.next;

            ListNode prev = null;
            ListNode current = head;
            for (int i = 1; i < count - n + 1; ++i) {
                prev = current;
                current = current.next;
            }
            if (prev != null) {
                if (current != null) prev.next = current.next;
                else prev.next = null;
            }

            return head;
        }
    }
}
