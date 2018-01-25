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
class Solution {
    
    // BFS with Queue
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        List<List<Integer>> s = new LinkedList<List<Integer>>(); // Stack: use as return var
        Queue<TreeNode> q = new LinkedList<>();    // Queue: store a certain level of Nodes
        
        // edge case:
        if(root == null) return s;
            
        // general case:
        q.offer(root);
        while(q.size() > 0){
            List<Integer> lvlNd = new LinkedList<>();
            int num = q.size();
            for(int i=0; i<num; i++){
                TreeNode temp = q.poll();
                lvlNd.add(temp.val);
                if(temp.left != null) q.offer(temp.left);
                if(temp.right!= null) q.offer(temp.right);
            }
            s.add(0,lvlNd);
        }
        return s;
    }
}

*/
public class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
            levelMaker(wrapList, root, 0);
            return wrapList;
        }
        
        public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
            if(root == null) return;
            if(level >= list.size()) {
                list.add(0, new LinkedList<Integer>());
            }
            
            levelMaker(list, root.left, level+1);
            levelMaker(list, root.right, level+1);
            list.get(list.size()-level-1).add(root.val);  // add to current level's next level
            /*
                |__|
                |__| <-- level
                |__| <-- root's level
                |__|
                |__|
            */
        }
    }