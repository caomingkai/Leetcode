/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
// version 1: not so good
public class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> arr = new ArrayList<>();
        int l = 0;
        while( head != null ){
            l++;
            arr.add(head.val);
            head = head.next;
        }
        if( l < 1) return true;
        int pivot = ( l - 1) / 2;
        
        for( int i = 0; i <= pivot; i++){
            if( !arr.get(i).equals( arr.get(l-i-1) ) ){
                return false;
            }
        }
        return true;
    }
}

*/
// version 2
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if(fast != null) slow = slow.next;

        slow = reverse(slow);
        while(slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }
        
        return slow == null;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}