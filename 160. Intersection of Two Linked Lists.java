/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 /*
 // version 1:  find out the diff length, and start at the point with same distance to the end, loop util find intersection item
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if( headA == null || headB == null ) return null;
        
        int la = length( headA );
        int lb = length( headB );
        
        // adjust headA align with headB
        while( la > lb ){
            headA = headA.next;
            la--;
        }
        
        // adjust headA align with headB
        while( la < lb ){
            headB = headB.next;
            lb--;
        }
        
        while( headA != headB ){
            headA = headA.next;
            headB = headB.next;
        }
        if( headA == null ) return null;
        
        return headA;
    }
    
    private int length( ListNode head ){
        int l = 0;
        while( head != null ){
            l++;
            head = head.next;
        }
        return l;
    }
}
*/

 // version 2:  no need find the length diff
 // let two pointer( headA headB ) loop through itself, and THEN loop the other list when it reach its end;
 // this way, headA and headB both go (lenghA + lengthB) step.
 // --- in the process, they both point to the other list, the two pointer will synchorously step forward until encouter target
 public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    
        if( headA == null || headB == null ) return null;
        ListNode a = headA;
        ListNode b = headB;
        
        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b ){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;// when headA reach its end, it point to headB, continue loop
            b = b == null ? headA : b.next;
        }
        
        return a;   // even if they don't have intersection, at last they point to 'null'
    }
}