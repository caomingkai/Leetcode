/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
            1 -> 2 -> 3 -> 4 -> 5 -> null
    
    null <- 1
    prev  curr next
    
    
    while( cur != null )
        next = cur.next
        cur.next = pre;
        cur = next;
        pre = cur;
*/
class Solution {
    
    /*
    // 1- iterative version
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        
        while( curr != null ){
            next = curr.next;
            curr.next = prev;
            if( next == null ) break;
            prev = curr;
            curr = next;
        }
        return curr;
    }
    */
    
    
    // 2- recursion version 
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        
        return h ( null, head);
    }
    
    /*                   1 -> 2 -> 3 -> 4
            1st: null <- 1  + h(1, 2)
            2nd  null <- 1 <- 2 + h(2, 3)
    */
    private ListNode h(ListNode prev, ListNode curr){
        if( curr.next == null ){
            curr.next = prev;
            return curr;
        }
        
        ListNode next = curr.next;
        curr.next = prev;
        return h( curr, next );
    }
}