/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */



// version 1: good version, in-place
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}

/*
// version 2: bad version, due to O(n) space complexity
class Solution {
    public ListNode reverseList(ListNode head) {
        
        if( head == null )
            return null;
        
        
        ListNode ptr1 = head;
        ListNode ptr2 = null;
        
        while( ptr1 != null ){
            ListNode shadow = new ListNode( ptr1.val );
            shadow.next = ptr2;
            ptr1 = ptr1.next;
            ptr2 = shadow;
        }
        head = ptr2;
        
        
        return head;
        
    }
}
*/