/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// version 1:  recursion with globle var
class Solution {
    private int sum;
    
    public int depthSum(List<NestedInteger> nestedList) {
            
        depthSumHelper( nestedList, 1 );
        return sum;
    }
    
    private void depthSumHelper( List<NestedInteger> nestedList, int lvl ){
        
        for( NestedInteger item: nestedList ){
            if( item.isInteger() )
                sum += item.getInteger() * lvl;
            else
                depthSumHelper( item.getList(), lvl+1 );
        }
    }
    
    
    
    
    /*
    // version 2:  recursion without globle var
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    public int depthSum(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger n : list) {
            if (n.isInteger()) {
                sum += n.getInteger() * depth;
            } else {
                sum += depthSum(n.getList(), depth + 1);
            }
        }
        return sum;
    }

    */

}



/*
// version 3/4: edge case consideration && iterative version 
public class Solution {
    // Recursive
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0)
            return 0;
        
        return helper(nestedList, 1);
    }
    
    private int helper(List<NestedInteger> list, int level) {
        if(list == null)
            return 0;

        int sum = 0;
        for(NestedInteger n : list) {
            if(n.isInteger())
                sum = sum + n.getInteger() * level;
            else
                sum = sum + helper(n.getList(), level + 1);
        }
        
        return sum;
    }
    
    // Iterative
    /*
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        
        for(int i = 0; i < nestedList.size(); i++)
            queue.offer(nestedList.get(i));
        
        int level = 0;
        int sum = 0;
        while(!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                NestedInteger curr = queue.poll();
                
                if(curr.isInteger())
                    sum = sum + curr.getInteger() * level;
                else {
                    List<NestedInteger> list = curr.getList();
                    
                    for(NestedInteger item : list)
                        queue.offer(item);                    
                }
            }
        }

        return sum;
    }
}
*/