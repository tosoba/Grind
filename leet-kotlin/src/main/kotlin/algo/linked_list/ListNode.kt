package algo.linked_list

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun listToNodes(list: List<Int>): ListNode? {
    var current: ListNode? = null
    var head: ListNode? = null
    val it = list.iterator()
    while (it.hasNext()) {
        if (current == null) {
            current = ListNode(it.next())
            head = current
        } else {
            current.next = ListNode(it.next())
            current = current.next
        }
    }
    return head
}