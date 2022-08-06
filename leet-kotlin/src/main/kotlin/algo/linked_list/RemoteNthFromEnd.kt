package algo.linked_list

private fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    if (head?.next == null) return null

    var forward = head
    repeat(n) { forward = forward?.next }

    if (forward == null) return head.next

    var it = head
    while (forward?.next != null) {
        forward = forward?.next
        it = it?.next
    }

    it?.next = it?.next?.next
    return head
}

fun main() {
    val h = removeNthFromEnd(listToNodes(listOf(1, 2)), 2)
}

