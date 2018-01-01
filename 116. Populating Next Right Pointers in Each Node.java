/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */




// verison 1: O(1) space
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        if(root.left!=null){
            root.left.next=root.right;
            if(root.next!=null)
                root.right.next=root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }
}


/*
// version 2: O(n) space
public class Solution {
    public void connect(TreeLinkNode root) {
        
        // edge case:
        if( root == null ) return;
        
        // general case:
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        q.offer(root);
        
        while( !q.isEmpty() ){
            int len = q.size();
            for( int i = 0; i < len-1; i++ ){
                TreeLinkNode cur = q.poll();
                TreeLinkNode nxt = q.peek();
                cur.next = nxt;
                if( cur.left != null ) q.offer(cur.left);
                if( cur.right != null ) q.offer(cur.right);
            }
            TreeLinkNode cur = q.poll();
            cur.next = null;
            if( cur.left != null ) q.offer(cur.left);
            if( cur.right != null ) q.offer(cur.right);
        }
        
    }
}

*/