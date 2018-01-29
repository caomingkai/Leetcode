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
// version 1: trverse by level: 每个node都记录对应pos
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if( root == null )
            return 0;
        
        int res = 0;
        
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> posQ = new LinkedList<>();
        
        nodeQ.offer(root);
        posQ.offer(1);
        
        while( !nodeQ.isEmpty() ){
            int len = nodeQ.size();
            int posLeft = posQ.peek();
            int width = 0;
            for( int i = 0; i < len; i++ ){
                TreeNode curr = nodeQ.poll();
                int pos = posQ.poll();
                width = pos - posLeft + 1;
                if( curr.left != null ){
                    nodeQ.offer(curr.left);
                    posQ.offer(2*pos);
                }
                if( curr.right != null ){
                    nodeQ.offer(curr.right);
                    posQ.offer(2*pos+1);
                }   
            }
            res = Math.max(res, width);
        } 
        return res;
    }
}
*/

// version 2: 
// 1 - dfs[pre-order]: 每个node都记录对应pos
// 2 - preorder, 每层第一个访问的点一定由该点建立起的该level数组
class Solution {
    private int res = 0;      // when only one node
    public int widthOfBinaryTree(TreeNode root) {
        if( root == null ) return 0;
        List<Integer> startPosList = new ArrayList<>();
        dfs( root, 1, 1, startPosList);
        return res;
    }
    
    // level 与 startPosList 的 index 关联; 
    // level1 <==> startPos[0]
    // level2 <==> startPos[1]
    private void dfs( TreeNode n, int lvl, int pos, List<Integer> startPosList ){
        if( n != null ){
            if( lvl > startPosList.size() )
                startPosList.add(pos);
            
            int startPos = startPosList.get(lvl-1);
            res = Math.max(res, pos-startPos+1 );
            dfs(n.left, lvl+1, 2*pos, startPosList );
            dfs(n.right, lvl+1, 2*pos+1, startPosList );
        }
    }
}

