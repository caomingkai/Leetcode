
/*

[1 2 3 4]

for i = 1 ~ n
    for j = i+1 ~ 

        1       2        3       4
      2 3 4   1 3 4    1 2 4   1 2 3
     3    1
   

*/

/*
// version1: Queue + iterative 
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        if( nums == null || nums.length == 0 )
            return res;
        
        int l = nums.length;
        Queue<List<Integer>> q = new LinkedList<>();
        for( int item: nums ) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(item);
            q.offer(tempList );
        }
        
        while( !q.isEmpty() ){
            List<Integer> tempList = q.poll();
            if( tempList.size() == l )
                res.add(tempList);
            else{
                HashSet<Integer> set = new HashSet<>(tempList);
                for( int item : nums ){
                    if( !set.contains(item) ){
                        ArrayList<Integer> newTemp = new ArrayList<>(tempList);
                        newTemp.add(item);
                        q.offer( newTemp );
                    }
                }
            }
        }
        return res;
    }
}
*/


// version 2:  backtrack with condition: avoid duplicate value
class Solution {


    /*
    
        [1 2 3]
    
    */
    public List<List<Integer>> permute ( int[] nums ){
    
        List<List<Integer>> res = new ArrayList<>();
        if( nums == null || nums.length == 0 )
            return res;
            
        List<Integer> prefix = new ArrayList<>();  // to store prefix solution
        backtrack(nums, prefix, 0, res );
        
        return res;
    }
    
    
    private void backtrack( int[] nums, List<Integer> prefix, int lvl, List<List<Integer>> res ){
        if( lvl == nums.length )
            res.add(new ArrayList<>(prefix) ); // must create a new ArrayList, otherwise the last element will be deleted by 'tempList.remove(tempList.size() - 1 );'
        else{
            for( int item: nums ){
                if( !prefix.contains(item) ){
                    List<Integer> tempList = new ArrayList<>(prefix);
                    tempList.add(item);
                    backtrack( nums, tempList, lvl+1, res);
                    tempList.remove(tempList.size() - 1 );
                }
            }
        }
    }
}


/*
// version 3: another method, grow increasingly from first i elements
// [] -> [1] -> [1 2] [2 1] -> [1 2 3] [1 3 2] [3 1 2] [2 1 3] [2 3 1] [3 2 1]

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        
        if( nums== null || nums.length == 0 )
            return res;
        res.add( new ArrayList<>() );
        
        for( int item: nums ){
            int size = res.size();
            for( int i = 0; i < size; i++ ){
                List<Integer> tempList = res.remove(0);
                for( int j = 0; j <= tempList.size(); j++ ){
                    List<Integer> newList = new ArrayList<>(tempList);
                    newList.add(j, item);
                    res.add(newList);
                }
            }
        }
        return res;
    }
}

*/