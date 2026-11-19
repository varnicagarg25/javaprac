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
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null || k==0){
            return head;
        }
        ListNode tail=head;
        int length=1;
        while (tail.next!=null){
            length++;
            tail=tail.next;
            
        }
        k=k%length;
        if (k==0){
            return head;
        }
        tail.next=head;
        ListNode newtail=head;
        int size=length-k;
        for(int i=1;i<size;i++){
            newtail=newtail.next;
        }
        ListNode newhead=newtail.next;
        newtail.next=null;
        return newhead;
    }
    
}
