/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    HashSet<Integer> set;
    public boolean findTarget(TreeNode root, int k) {
        
        set = new HashSet<>();
        
        return dfsFind( root, k );
    }
    
    private boolean dfsFind( TreeNode n, int k ){
        if( n== null ) return false;
        
        if( set.contains( n.val ) ) return true;
        
        set.add( k-n.val );
        if( dfsFind( n.left, k ) ) return true;
        if( dfsFind( n.right, k ) ) return true;
        
        return false;
    }
}