/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
// version 1:  recursive version
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}

/*
// version 2:  iteration, handle head exclusively
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if( head == null ) return head;
        while( head != null && head.val == val){
            if (head.val == val ) head = head.next;
        }
        if( head == null ) return head;
        
        ListNode t = head;
        while( t.next != null ){
            if( t.next.val == val ){
                t.next = t.next.next;
            }else{
                t = t.next;
            }
        }
        return head;
    }
}


// version 3:  iteration, treat the  head as general scenario
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode curr = head, prev = fakeHead;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = prev.next;
            }
            curr = curr.next;
        }
        return fakeHead.next;
    }
}