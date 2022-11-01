package algo.linked_list

fun hasCycle(head: ListNode?): Boolean {
    if (head?.next == null) return false

    var slow = head
    var fast = head.next
    while (slow != null && fast != null) {
        if (slow == fast) return true
        slow = slow.next
        fast = fast.next?.next
    }
    return false
}