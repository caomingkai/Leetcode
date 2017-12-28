/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        
        if( head == null) 
            return null;
        if( head.next == null )
            return new TreeNode(head.val);
    
        // find out the root-to-be, and its left children, right children
        ListNode[] res = split( head );
        TreeNode root = new TreeNode( res[1].val );
        root.left = sortedListToBST( res[0] );
        root.right = sortedListToBST( res[2] );
            
        return root;
    }
    
    private ListNode[] split( ListNode head ){
        
        ListNode[] res = new ListNode[3];
        
        ListNode fast = head.next;
        ListNode slow = head;
        
        while( fast.next != null && fast.next.next != null ){
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode leftHead = head;
        ListNode root = slow.next;
        ListNode rightHead = slow.next.next;
        
        slow.next.next = null;
        slow.next = null;
        
        res[0] = leftHead;
        res[1] = root;
        res[2] = rightHead;
        
        return res;
    }
}


*/
// version 2: 
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;

        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }
}