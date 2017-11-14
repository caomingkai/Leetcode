/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
class Solution {
    public ListNode insertionSortList(ListNode head) {
		if( head == null ){
			return head;
		}
		
		ListNode helper = new ListNode(0); //new starter of the sorted list
		ListNode cur = head; //the node will be inserted
		ListNode pre = helper; //insert node between pre and pre.next
		ListNode next = null; //the next node will be inserted
		//not the end of input list
		while( cur != null ){
			next = cur.next;
			//find the right place to insert
			while( pre.next != null && pre.next.val < cur.val ){
				pre = pre.next;
			}
			//insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		
		return helper.next;
	}
}
*/

// VERSION 2:  using array to sort beforehand, then write to the linkedlist
class Solution {
    public ListNode insertionSortList(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;
        }
        
        current = head;
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = current.val;
            current = current.next;
        }

        Arrays.sort(array);
        current = head;
        for (int i = 0; i < len; i++) {
            current.val = array[i];
            current = current.next;
        }
        return head;
    }
}