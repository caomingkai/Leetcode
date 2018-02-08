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
// version 1:   recursion
//              ----  “累减，而不是累加”  
//              ----  “严格以leafNode结束，而不是NULL节点”
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if(root == null ) return false;
        
        if( root.left == null && root.right == null && root.val == sum ) return true;
        if( root.left != null && hasPathSum(root.left, sum-root.val) ) return true;
        if( root.right != null && hasPathSum(root.right, sum-root.val) ) return true;
        
        return false;
    }
}
*/

/* version 2:   iterative
                0. 两个 stack； 一个压入后续要处理的node； 另一个压入当时对应curSum
                1. 每到一个Node，
                    if(两个孩子都 != null ) 压入right，处理left
                    if(有一个孩子 == null ) 处理这个孩子
                    if(两个孩子都 == null ) 检查是否 == target
*/ 
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if( root == null ) return false;
        
        Deque<TreeNode> s1 = new LinkedList<>();  // 压入right child
        Deque<Integer> s2 = new LinkedList<>();   // 压入当时(在压入right前)对应的curSum
        
        
        s1.push( root );
        s2.push( 0 );
        
        while( !s1.isEmpty() ){
            TreeNode n = s1.pop();
            int curSum = s2.pop();
            curSum += n.val;
            
            if(  n.left == null && n.right == null && curSum == sum)
                    return true;
            if( n.left != null  ){
                s1.push(n.left);
                s2.push(curSum);
            }
            if( n.right != null  ){
                s1.push(n.right);
                s2.push(curSum);
            }
        }
        return false;
    }
}


/*
// BUG: (1,2) 未过
//【出错原因】： base case 没有体现出 leafNode 的特性 （node.left == null && node.right == null ）
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        int curSum = 0;
        if( root == null ) return false;
        return dfs( root, curSum, sum );
    }
    
    // 【出错原因】： base case 没有体现出 leafNode 的特性 （node.left == null && node.right == null ）
    private boolean dfs( TreeNode node, int curSum , int target){
        if( node == null ){
            if( curSum == target)
                return true;
            else 
                return false;
        }
        boolean flagLeft = dfs( node.left, curSum+node.val, target);
        boolean flagRight = dfs( node.right, curSum+node.val, target);
        if(flagLeft || flagRight)
            return true;
        return false;
    }
}
*/