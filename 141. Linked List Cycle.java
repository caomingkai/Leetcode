/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/*
                   __________
                  |          |     
        1 -> 2 -> 3 -> 4  -> 5
       | |
       s f

*/
public class Solution {
   public boolean hasCycle(ListNode head) {
    
        if( head == null )
            return false;
            
        ListNode s = head;
        ListNode f = head;
        
        while( f.next != null ){
            s = s.next;
            f = f.next.next;
            
            if( s == f )
                return true;
            if( f == null )
                return false;
        }
        
        return false;
    }
}
