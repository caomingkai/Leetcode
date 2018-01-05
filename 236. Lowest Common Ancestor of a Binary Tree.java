/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// key idea: helper method returns 
// 1. either LCA 
// 2. or one of p & q, 
// 3. or even null( in case none belongs to a subtree)


// 类似大老板问小老板：找到同时认识A与B的人；如果没有这个人，告诉我哪个人在咱们的团队；如果都没有，告诉我没有。
// 小老板问小小老板：  找到同时认识A与B的人；如果没有这个人，告诉我哪个人在咱们的团队；如果都没有，告诉我没有。
// 小小老板问小小小老板：。。。。。
// 直到:
// -- 问到A或B本人：向上级一层层返回---“A在这个团队。。”
// -- 或者,最底层的小兵：“查无此人。。。”
// -- 或者，到了某层C老板那里，收到了左膀回复：“我们团队有A”，也收到了右臂回复“我们团队有B”；遂向上级回复：“我认识A与B！！”
// -- 上级老板向上上级老板回复：“C认识A与B”，依次类推

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper( root, p ,q );
    }
    
    private TreeNode helper( TreeNode root, TreeNode p, TreeNode q ){
        // base case:
        if( root == null ) return null;
        if( root == p || root == q ) return root;
        
        // recursive case:
        TreeNode left = helper( root.left, p, q );
        TreeNode right = helper( root.right, p, q);
        
        if( left != null && right != null ) return root;
        
        if( left != null && right == null ) return left;
        
        if( left == null && right != null ) return right;
        
        return null;
    }
}