/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        // Dummy node avoids edge cases when left == 1
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        // 1. Move `prev` to the node right before `left`
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        // `curr` points to the `left`-th node, which becomes the tail of the reversed sublist
        ListNode curr = prev.next;

        // 2. Repeatedly pull the node after `curr` and insert it right after `prev`
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }

        return dummy.next;
    }
}