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
 -0- 用slower和faster方法判断是否有环；
     设链表的头节点是head，环的入口节点是entry，slower和faster2个指针相遇的节点是meeting;
     L1是head到entry的正向距离
     L2是entry到meeting的正向距离，
     C是环的长度，
     n是faster指针在cycle里遍历的次数(不到一遍算0)
     
 -1- 当slower和faster相遇时，slower已经走了L1 + L2的距离，也即head和meeting的距离;
 -2- 当slower和faster相遇时，faster已经走了L1 + L2 + C的距离;
 -3- 因为slower步进1，而faster步进2，那么当slower和faster第一次相遇时，faster已走的距离是slower已经走的距离的两倍，即
     2* (L1 + L2) = L1 + L2 +  C    ===>   L1 = C - L2
 -4- L1 = C - L2 这个等式表明， head和entry的距离(L1)，等于meeting到entry的正向距离（链表是有遍历方向的）。

 -5- ====>  L1 = C - L2
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