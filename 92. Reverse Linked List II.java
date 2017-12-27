/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode firstSegEnd = dummy;
        
        for( int i = 0; i < m-1; i++ ){
            firstSegEnd = firstSegEnd.next;
        }
        
        
        ListNode middleSegStart = firstSegEnd.next;
        ListNode ptr = middleSegStart;
        for( int i = 0; i < n-m; i++ ){
            ListNode nn = ptr.next.next;
            ptr.next.next = middleSegStart;
            ListNode hh = ptr.next;
            ptr.next = nn;
            middleSegStart = hh;
        }
        firstSegEnd.next = middleSegStart;
        
        
        return dummy.next;
        
    }
}