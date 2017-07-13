/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 1: iterative ( queue )
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(q.size()>0){
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                list.add(node.val);
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
            result.add(0,list);
        }
        return result;

    }
}

/*
// version 2: recursive( DFS )
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
            list.get(list.size()-level-1).add(root.val);
        }
}

*/