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
// version 1: bfs with Queue
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // edge case:
        List<Integer> res = new ArrayList<>();
        if( root == null ) return res;
        
        // general case:
        
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while( q.size() > 0 ){
            int num = q.size();
            for( int i = 0; i < num; i++ ){
                TreeNode temp = q.poll();
                if( i == 0 ) res.add( temp.val );
                if( temp.right != null ) q.offer( temp.right );
                if( temp.left  != null ) q.offer( temp.left );
            }
        }
        return res;
    }
}
*/

// version 2: dfs
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // edge case:
        List<Integer> res = new ArrayList<>();
        if( root == null ) return res;
        
        // general case:
        HashSet<Integer> s = new HashSet<>(); // to keep track of level, rejecting latter nodes(not right most nodes)
        DFS( root, s, 0, res);
        
        return res;
    }
    
    private void DFS( TreeNode root, HashSet<Integer> s, int level, List<Integer> res ){
        
        // base case:
        if( root == null ) return;
        
        // recursive case:
        if( !s.contains(level) ){
            s.add( level );
            res.add( root.val );
        }
        DFS( root.right, s, level+1, res );
        DFS( root.left,  s, level+1, res );
    }
}