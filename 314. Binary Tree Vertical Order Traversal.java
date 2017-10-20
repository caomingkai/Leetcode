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
    /*
    // version 1: DFS with little fault
    public List<List<Integer>> verticalOrder(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        if( root == null )
            return res;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        helper( root , 0 , map );
        
        for( int i: map.keySet() ){
            res.add( map.get(i) );
        }
        
        return res;
    }
    
    private void helper( TreeNode root, int col, TreeMap<Integer, List<Integer>> map ){
        if( root != null ){
            List<Integer> list = map.getOrDefault( col, new ArrayList<>() );
            list.add( root.val );
            map.put( col, list );
            
            helper( root.left, col-1, map );
            helper( root.right, col+1, map );
        }
    }
    */
    
    // version 1: DFS
    
    class Entry{
        TreeNode n;
        int i;
        Entry( TreeNode n, int i){
            this.n = n;
            this.i = i;
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        if( root == null )
            return res;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue< Entry > que = new LinkedList<>();
        
        que.offer( new Entry(root,0) );
        while( que.size() > 0 ){
            Entry e = que.poll();
            List<Integer> list = map.getOrDefault(e.i, new ArrayList<>());
            list.add(e.n.val);
            map.put(e.i, list );
            
            if( e.n.left != null )
                que.offer( new Entry(e.n.left, e.i-1) );
            if( e.n.right != null )
                que.offer( new Entry(e.n.right, e.i+1) );
        }
        
        for( int i: map.keySet() ){
            res.add( map.get(i) );
        }
        
        return res;
    }

}