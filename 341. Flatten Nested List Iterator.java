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
public class NestedIterator implements Iterator<Integer> {
    
    private int pointer;
    private ArrayList<Integer> innerList;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        pointer = 0;
        innerList = new ArrayList<Integer>();
        
        convertNestedList(nestedList, innerList);
        
        // reset pointer
        pointer = 0;
    }

    private void convertNestedList( List<NestedInteger> nestedList,  ArrayList<Integer> innerList){
        int n = nestedList.size();
        
        for( int i = 0; i < n; i++ ){
            NestedInteger cur = nestedList.get(i);
            if( cur.isInteger() ){
                innerList.add( cur.getInteger() );
                pointer++;
            }else{
                List<NestedInteger> curList = cur.getList();
                convertNestedList(curList, innerList);
            }    
        }
    }
    
    @Override
    public Integer next() {
        int res = innerList.get(pointer);
        pointer++;
        return res;
    }

    @Override
    public boolean hasNext() {
        return pointer < innerList.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */