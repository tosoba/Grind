package leetcode.ll;

public class ReverseLinkedListBetween {
    private static class ReverseResult {
        ListNode reversedHead;
        ListNode postReversed;

        public ReverseResult(ListNode reversedHead, ListNode postReversed) {
            this.reversedHead = reversedHead;
            this.postReversed = postReversed;
        }
    }

    private ReverseResult reverseListIterative(ListNode head, int limit) {
        var reversedHead = head;
        var it = head.next;
        reversedHead.next = null;
        ListNode postReversed = null;
        int counter = 0;

        while (it != null) {
            var tmp = it.next;
            it.next = reversedHead;
            reversedHead = it;
            it = tmp;
            ++counter;
            if (counter == limit) {
                postReversed = it;
                break;
            }
        }

        return new ReverseResult(reversedHead, postReversed);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (left == right) return head;

        var it = head;
        ListNode preReversed = null;
        int counter = 1;
        while (it != null && counter < left) {
            if (it.next != null && counter + 1 == left) {
                preReversed = it;
            }
            it = it.next;
            ++counter;
        }

        if (it != null) {
            var result = reverseListIterative(it, right - left);
            if (preReversed != null) {
                preReversed.next = result.reversedHead;
            }
            it.next = result.postReversed;
            return left == 1 ? result.reversedHead : head;
        }

        return head;
    }

    public static void main(String[] args) {
        var a = new ReverseLinkedListBetween().reverseBetween(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2, 4);
        var b = new ReverseLinkedListBetween().reverseBetween(new ListNode(3, new ListNode(5)), 1, 2);
        var c = new ReverseLinkedListBetween().reverseBetween(new ListNode(1, new ListNode(2, new ListNode(3))), 1, 2);

    }

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
}
