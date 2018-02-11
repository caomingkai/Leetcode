
/*
        
    【注意】： 不能遍历每个node与left、node与right的差值，取最小值！！ 这是错的
             例如      5   , [5,4]最小
                     /  \
                    2    6
                   / \
                  1   4 
                  
    【方法】 中序遍历
*/
class Solution {
    
     public int minDiffInBST(TreeNode root) {
         int res = Integer.MAX_VALUE;
         
         Deque<TreeNode> stack = new LinkedList<>();
         TreeNode prev = null;
         while( !stack.isEmpty() || root != null ){
             while( root != null ){
                 stack.push(root);
                 root = root.left;
             }
             root = stack.pop();
             if( prev != null )
                 res = Math.min( res, root.val - prev.val );
             prev = root;
             root = root.right;
         }
         return res;
     }
    
    
}