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
    class Parent{
        TreeNode t;
        boolean f;
        Parent( TreeNode t, boolean f){
            this.t = t;
            this.f = f;
        }
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if( root == null )
            return res;
        
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        HashMap<TreeNode, Parent> map = new HashMap<>();
        map.put( root, new Parent( dummy, true) );
        
        while( root.left != null || root.right != null ){
            
            List<Integer> list = new ArrayList<>();
            Deque<TreeNode> stack = new LinkedList<>();
            
            stack.push(root);
            while( stack.size() > 0 ){
                TreeNode n = stack.pop();
                if( n.left == null && n.right == null ){
                    list.add(n.val);
                    Parent p = map.get(n);
                    TreeNode pNode = p.t;
                    boolean flag = p.f;
                    if( flag )
                        pNode.left = null;
                    else 
                        pNode.right = null;
                }else{
                    TreeNode left = n.left;
                    TreeNode right = n.right;
                    
                    if( right != null ){
                        stack.push( right );
                        map.put( right, new Parent( n, false) );
                    }
                    
                    if( left != null ){
                        stack.push( left );
                        map.put( left, new Parent( n, true) );
                    }
                }
            }
            res.add(list);
        }
        

        
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        res.add(list);
        return res;
    }
    
}