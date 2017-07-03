/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 1: global var recursion
public class Solution {
     int s = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        helper( root );
        return s;
    }
    
    private void helper( TreeNode r ){
        
        if( r == null ) return;
        if( r.left != null){
            if( r.left.left == null && r.left.right == null ) s += r.left.val;
        }
        helper(r.left);
        helper(r.right);
    }
}


/*
// version 2: no global var
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        ArrayList<Integer> l = new ArrayList();
        helper( root , l);
        
        int sum = 0;
        for( int i = 0; i < l.size(); i++ ){
            sum += l.get(i);
        }
        return sum;
    }
    
    private void helper( TreeNode r , ArrayList<Integer> l){
        
        if( r == null ) return;
        
        if( r.left != null){
            if( r.left.left == null && r.left.right == null ) 
                l.add(r.left.val);
        }
        helper( r.left, l) ;
        helper( r.right, l);
    }
}
*/