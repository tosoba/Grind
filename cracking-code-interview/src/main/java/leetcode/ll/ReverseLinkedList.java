package leetcode.ll;

public class ReverseLinkedList {
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

    public ListNode reverseListIterative(ListNode head) {
        if (head == null) return null;

        var reversedHead = head;
        var it = head.next;
        reversedHead.next = null;

        while (it != null) {
            var tmp = it.next;
            it.next = reversedHead;
            reversedHead = it;
            it = tmp;
        }

        return reversedHead;
    }

    private ListNode reverseOrder(ListNode first, ListNode second, boolean calledWithHead) {
        if (second == null) return first;
        var next = second.next;
        second.next = first;
        if (calledWithHead) first.next = null;
        return reverseOrder(second, next, false);
    }

    public ListNode reverseListRecursive(ListNode head) {
        if (head == null) return null;
        return reverseOrder(head, head.next, true);
    }

    public static void main(String[] args) {
        var a = new ReverseLinkedList().reverseListIterative(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
        var b = new ReverseLinkedList().reverseListRecursive(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
    }
}
