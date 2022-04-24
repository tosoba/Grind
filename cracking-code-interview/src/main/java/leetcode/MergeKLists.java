package leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class MergeKLists {
    public static class ListNode {
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
        public ListNode mergeKLists(ListNode[] lists) {
            var currents = new ArrayList<ListNode>(lists.length);
            for (var i = 0; i < lists.length; ++i) {
                if (lists[i] != null) {
                    currents.add(lists[i]);
                }
            }

            ListNode combinedHead = null;
            ListNode combinedCurrent = null;
            while (!currents.isEmpty()) {
                ListNode next = null;
                int currentIndex = -1;
                for (var i = currents.size() - 1; i >= 0; --i) {
                    var current = currents.get(i);
                    if (next == null || current.val < next.val) {
                        next = current;
                        currentIndex = i;
                    }
                }
                if (combinedCurrent == null) {
                    combinedHead = next;
                    combinedCurrent = next;
                } else {
                    combinedCurrent.next = next;
                    combinedCurrent = combinedCurrent.next;
                }

                if (next.next == null) currents.remove(currentIndex);
                else currents.set(currentIndex, next.next);
            }
            return combinedHead;
        }

        public ListNode mergeKLists2(ListNode[] lists) {
            var values = new ArrayList<Integer>();
            for (var list : lists) {
                var current = list;
                while (current != null) {
                    values.add(current.val);
                    current = current.next;
                }
            }

            Collections.sort(values);

            ListNode head = null;
            ListNode current = null;
            for (var val : values) {
                if (head == null) {
                    head = new ListNode(val);
                    current = head;
                } else {
                    current.next = new ListNode(val);
                    current = current.next;
                }
            }
            return head;
        }
    }
}
