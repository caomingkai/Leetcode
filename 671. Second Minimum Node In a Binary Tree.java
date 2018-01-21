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
              2
           /    \
          2      5
         / \    / \
        2   3  5   7
    
    character for this tree: like MinHeap, except 
                             1. it has exactly two child (heap: has one or two )
                             2. parent node is equal to one of its child(heap: parent val <= it children)
    second smallest value only exist right under node with same value as root.
    so we traverse the tree, check all nodes under nodes with value-2, and select the min one
*/
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        int res = helper( root, root, Integer.MAX_VALUE );
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    // only care 3 cases:
    // 1. curRoot is null, just return Integer.MAX_VALUE, denoting no smaller one on this branch
    // 2. curRoot.val == root.val, keep checking it children
    // 3. curRoot.val != root.val, compare with curMin, return smaller on
    
    private int helper( TreeNode curRoot, TreeNode root, int secondMin ){
        if( curRoot == null )
            return Integer.MAX_VALUE;     // don't have second min value, denote as: Integer.MAX_VALUE
        else if( curRoot.val == root.val )
            return Math.min( secondMin, Math.min(helper(curRoot.left, root, secondMin ), 
                                                 helper(curRoot.right, root, secondMin) ) );
        else
            return Math.min( secondMin, curRoot.val ); // return smaller one
    }
}