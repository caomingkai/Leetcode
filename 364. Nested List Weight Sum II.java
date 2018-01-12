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



/*

[1,2,[3,4,[5, 6]], 7,[3,4,[5, 6]], 8 ]

Sum1: 1+2+7+8=18 

Sum2: 3 + 4  = 7
Sum3: 5+6 = 11

[ 18, 7, 11 ]

6*1 + (3+4)*2 + (1+2)*3

6*3 + (3+4)*2 + (1+2)*1



*/
class Solution {
    /*
    // version 1: find out the level info, then do the multiplication
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int res = 0;
        Queue<List<NestedInteger>> q = new LinkedList<>();
        List<Integer> lvlSum = new ArrayList<>();
        int lvl = 0;
        q.offer( nestedList );
        
        while( !q.isEmpty() ){
            int l = q.size();
            int sum = 0;
            lvl++;
            for( int i = 0; i < l; i++ ){
                List<NestedInteger> list = q.poll();
                for( NestedInteger item: list ){
                    if( item.isInteger() )
                        sum += item.getInteger();
                    else
                        q.offer(item.getList() );
                }      
            }
            lvlSum.add(sum);
        }
        
        for( int i = 0; i < lvl; i++ ){
            res += lvlSum.get(i)*(lvl-i);
        }
        
        return res;
    }
    */
    
    // version 2: no multiplication -> just add mulitple times
    public int depthSumInverse(List<NestedInteger> nestedList) {
    int unweighted = 0, weighted = 0;
    while (!nestedList.isEmpty()) {
        List<NestedInteger> nextLevel = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger())
                unweighted += ni.getInteger();
            else
                nextLevel.addAll(ni.getList());
        }
        weighted += unweighted;
        nestedList = nextLevel;
    }
    return weighted;
}
}



