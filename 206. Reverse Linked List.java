/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if( head == null ) return head;
        ListNode tail = head; // after reversion, the head would be the tail, and tail pointer wouldn't change in process
        while( tail.next != null ){
            ListNode nextE = tail.next;
            tail.next = nextE.next;
            nextE.next = head;
            head = nextE;
        }
        return head;
    }
}