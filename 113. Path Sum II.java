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
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        if( root == null ) return res;
        
        LinkedList<Integer> mdlRes = new LinkedList<>();
        
        backtrack( root, sum, mdlRes, res );
        return res;
    }
    
    private void backtrack( TreeNode root, int sum, LinkedList<Integer> mdlRes, List<List<Integer>> res){
        
        mdlRes.push(root.val);
        
        if( root.left == null && root.right == null && root.val == sum ){
           
            LinkedList<Integer> success = new LinkedList<>();
            for( Integer i : mdlRes ){ success.addFirst(i); }
            res.add(success);
        }
        
        if( root.left != null )
            backtrack( root.left, sum-root.val, mdlRes, res);
        
        if( root.right != null )
            backtrack( root.right, sum-root.val, mdlRes, res);
        
        mdlRes.pop();
        
    }
}