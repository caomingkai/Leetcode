/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// version 1: iteration ( queue)
public class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Double> res = new LinkedList<>();
        
        if( root == null ) return res;
        
        queue.offer(root);
        while( queue.size() > 0 ){
            int num = queue.size();  // keep track of # of the nodes in current level
            double sum = 0;
            for( int i = 0; i < num; i++ ){
                TreeNode temp = queue.poll();
                sum += temp.val;
                if( temp.left != null ) queue.offer( temp.left );
                if( temp.right != null) queue.offer( temp.right);
            }
            res.offer(sum/num);
        }
        return res;
    }
}

// version 2: recursion( DFS )
public class Solution {
    public List < Double > averageOfLevels(TreeNode root) {
        List < Integer > count = new ArrayList < > ();
        List < Double > res = new ArrayList < > ();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }
    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }
}