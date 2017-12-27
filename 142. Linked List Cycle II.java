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
public class Solution {
    public ListNode detectCycle(ListNode head) {
        
        if( head == null )
            return null;
        
        ListNode meetingPnt = getMeetingPnt( head );
        if( meetingPnt == null )
            return null;
        
        ListNode ptr1 = head;
        ListNode ptr2 = meetingPnt;
        
        while( ptr1 != ptr2 ){
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
            
        return ptr1;
    }
    
    
    private ListNode getMeetingPnt( ListNode head ){
        ListNode slow = head;
        ListNode fast = head;
        
        while( fast != null ){
            slow = slow.next;
            if( fast.next != null )
                fast = fast.next.next;
            else
                break;
            
            if( slow == fast )
                return slow;
        }
        
        return null;
    }
}