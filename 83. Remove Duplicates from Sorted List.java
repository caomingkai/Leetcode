/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        /*
        ListNode h = head;
        
        while( h != null ){
            if( h.next != null && h.val == h.next.val ){
                h.next = h.next.next;
            }else{
                h = h.next;
            }
        }
        return head;
        */
        // version 2 : recursive 
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}