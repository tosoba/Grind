package leetcode.ll;

public class ReverseNodesInKGroup {
    private static class ReverseBetweenResult {
        ListNode reversedHead;
        ListNode postReversed;

        public ReverseBetweenResult(ListNode reversedHead, ListNode postReversed) {
            this.reversedHead = reversedHead;
            this.postReversed = postReversed;
        }
    }

    private static class HeadTail {
        ListNode head;
        ListNode tail;

        public HeadTail(ListNode head, ListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    private ReverseBetweenResult reverseListIterative(ListNode head, int limit) {
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

        return new ReverseBetweenResult(reversedHead, postReversed);
    }

    public HeadTail reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (left == right) return new HeadTail(head, head);

        var it = head;
        ListNode preReversed = null;
        int counter = 1;
        while (it != null && counter < left) {
            if (it.next != null && counter + 1 == left) preReversed = it;
            it = it.next;
            ++counter;
        }

        if (it != null) {
            var result = reverseListIterative(it, right - left);
            if (preReversed != null) preReversed.next = result.reversedHead;
            it.next = result.postReversed;
            return left == 1 ? new HeadTail(result.reversedHead, it) : new HeadTail(head, it);
        }

        return new HeadTail(head, head);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        int nodesCount = 1;
        ListNode it = head.next;
        while (it != null) {
            ++nodesCount;
            it = it.next;
        }

        ListNode joinedHead = null;
        HeadTail prevHT = null;
        for (var i = 0; i <= nodesCount - k; i += k) {
            var nextHT = reverseBetween(prevHT != null && prevHT.tail != null && prevHT.tail.next != null ? prevHT.tail.next : head, 1, k);
            if (joinedHead == null) {
                joinedHead = nextHT.head;
            } else {
                prevHT.tail.next = nextHT.head;
            }
            prevHT = nextHT;
        }
        return joinedHead;
    }

    public static void main(String[] args) {
        var a = new ReverseNodesInKGroup().reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
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
