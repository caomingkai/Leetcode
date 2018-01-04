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
// version 1: my backtrack
class Solution {
    
    int res = 0;
    
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,1);
        
        helper(root, sum, 0, map);
        return res;
    }
    
    private void helper( TreeNode root, int target, int curSum, HashMap<Integer, Integer> map ){
        if( root == null )
            return;
        
        int newSum = root.val + curSum;
        if( map.containsKey( newSum-target ) ){
            res += map.get( newSum-target );
        }
        map.put( newSum, map.getOrDefault(newSum, 0) + 1 );
        
        if( root.left != null ) helper(root.left, target, newSum, map);
        if( root.right != null ) helper(root.right, target, newSum, map);
        
        map.put(newSum, map.get(newSum) - 1);
    }
     
}
*/

// version 2: 
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    public int findPath(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }
   
}