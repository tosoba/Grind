package algo.linked_list

private fun reverseList(head: ListNode?): ListNode? {
    if (head?.next == null) return head

    var newHead: ListNode? = null
    var left = head
    var right = head.next
    while (left != null && right != null) {
        reversePair(left, right, newHead == null).also {
            left = it.first
            right = it.second
        }
        if (right != null) newHead = right
    }
    return newHead ?: left
}

private fun reversePair(left: ListNode, right: ListNode, leftIsLast: Boolean = false): Pair<ListNode, ListNode?> {
    val next = right.next
    right.next = left
    if (leftIsLast) left.next = null
    return right to next
}

fun main() {
    val ans = reverseList(listToNodes(listOf(1, 2, 3, 4, 5)))
}