package algo.linked_list

private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    var it1 = list1
    var it2 = list2
    var head: ListNode? = null
    var current: ListNode? = null

    fun appendToMerged(value: Int) {
        if (current == null) {
            head = ListNode(value)
            current = head
        } else {
            current?.next = ListNode(value)
            current = current?.next
        }
    }

    fun appendToMerged(list: ListNode?) {
        current?.let { it.next = list } ?: run { head = list }
    }

    while (it1 != null || it2 != null) {
        if (it1 != null && it2 != null) {
            if (it1.`val` < it2.`val`) {
                appendToMerged(it1.`val`)
                it1 = it1.next
            } else {
                appendToMerged(it2.`val`)
                it2 = it2.next
            }
        } else {
            appendToMerged(it1 ?: it2)
            break
        }
    }

    return head
}
