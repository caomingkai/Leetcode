/*
1 
2

1 1
1 2
2 1

1 1 2
1 2 1
2 1 1

maintain a HashMap contains the items left needs to be inserted(only nums>0, we could use it)
when  prefix.size() == max, add to res
*/
/*

[1 1 2 3]

1 
2
3

1 1
1 2
1 3

2 1
2 3
3 1
3 2

*/

class Solution {

    public List<List<Integer>> permuteUnique( int[] nums ){
    
        List<List<Integer>> res = new ArrayList<>();
        if( nums == null || nums.length == 0 ) 
            return res;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for( int item: nums) map.put( item, map.getOrDefault(item,0) + 1 );
        List<Integer> prefix = new ArrayList<>();
        backtrack( prefix, map, res, nums.length );
        return res;
    }
    
    private void backtrack( List<Integer> prefix, HashMap<Integer, Integer> map, List<List<Integer>> res, int max){
    
        if( prefix.size() == max )
            res.add( new ArrayList(prefix) );
        else{
            for( int key: map.keySet() ) {
                if( map.get(key) > 0 ){
                    prefix.add( key );
                    map.put(key, map.get(key)-1);
                    backtrack( prefix, map, res, max);
                    map.put(key, map.get(key)+1);
                    prefix.remove( prefix.size() - 1 );
                }
            }
            
        }
    }
}