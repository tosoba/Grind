package algo.linked_list

private fun deleteDuplicates(head: ListNode?): ListNode? {
    if (head?.next == null) return head
    if (head.next?.next == null) {
        return if (head.`val` == head.next?.`val`) null
        else head
    }

    var newHead = head
    while (newHead != null && newHead.`val` == newHead.next?.`val`) {
        while (newHead?.`val` == newHead?.next?.`val`) {
            newHead = newHead?.next
        }
        if (head !== newHead) newHead = newHead?.next
    }

    var left = newHead
    var it = newHead?.next
    while (it != null) {
        var removed = false
        while (it?.`val` == it?.next?.`val`) {
            it = it?.next
            removed = true
        }

        if (removed) {
            left?.next = it?.next
            it = left?.next
        } else {
            left = left?.next
            it = it?.next
        }
    }

    if (newHead?.next?.next == null) {
        return if (newHead?.`val` == newHead?.next?.`val`) null
        else newHead
    }

    return newHead
}

fun main() {
//    printList(deleteDuplicates(listToNodes(listOf(1, 2, 3, 3, 4, 4, 5))))
//    printList(deleteDuplicates(listToNodes(listOf(1, 1, 1, 2, 3))))
//    printList(deleteDuplicates(listToNodes(listOf(1, 2, 2, 2,3,3))))
//    printList(deleteDuplicates(listToNodes(listOf(1, 1, 2, 2))))
    printList(deleteDuplicates(listToNodes(listOf(1, 1, 1))))
}