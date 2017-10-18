/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return helper( nestedList, 1 );
    }
    
    private int helper( List<NestedInteger> list, int level ){
        int l = list.size();
        int sum = 0;
        for( int i = 0; i < l; i++ ){
            NestedInteger temp = list.get(i);
            if( temp.isInteger() ){
                sum += temp.getInteger()*level;
            }else{
                sum += helper( temp.getList(), level+1);
            }
        }
        
        return sum;
    }
}