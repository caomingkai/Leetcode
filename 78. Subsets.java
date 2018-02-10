/*  

    version 1:  recursive = backtrack with condition
    按照subset的大小从size=1,逐渐增大到size=3
    过程：[ ] -> [1] [2] [3] -> [1,2] [1,3] [2,3] -> [1,2,3] 
    
    按顺序来------按顺序来------按顺序来------按顺序来------按顺序来-------按顺序来
    不往回看------不往回看------不往回看------不往回看------不往回看-------不往回看
    1 2， 1 3， 1 4， 1 5
    2 3， 2 4， 2 5
    3 4， 3 5
    4 5


*/

class Solution {
    List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> prefix = new ArrayList<>();
        backtrack( prefix, 0, nums  );
        return res;
    }
    
    // prefix放在参数前是由意义的：
    // 该函数功能： 1- 先将prefix放入解空间；
    //            2- 然后从nums的第startIdx元素开始，依次在prefix后拼接新的元素构成subset
    private void backtrack( List<Integer> prefix, int startIdx, int[] nums){

        res.add( new ArrayList<>(prefix) );
        if( startIdx > nums.length ) return;   // 实际上不必要， for循环会检查
        
        for( int i = startIdx; i < nums.length; i++ ){
            prefix.add(nums[i]);
            backtrack( prefix, i+1, nums );
            prefix.remove(prefix.size()-1);
        }
        
    }
}

/*  
    version 2:  recursive
    从小的set开始形成subset，随着set元素的增加，在原来 subset的基础上加入新元素
    过程： [] [1] ->[] [1] + [2] [1,2] ->[] [1] [2] [1,2] + [3] [1,3] [2,3] [1,2,3]

*/

/*
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> prefix = new ArrayList<>();
        res.add(prefix);
        createSubset( 0, nums, prefix );
        return res;
    }
    
    private void createSubset( int i, int[] nums, List<Integer> prefix){
        if( i == nums.length )
            return;
        
        List<List<Integer>> temp = new ArrayList<>();
        for( List<Integer> list : res ){
            List<Integer> newList = new ArrayList<>(list);
            newList.add(nums[i]);
            temp.add(newList);
        }
        for( List<Integer> item : temp )
            res.add( new ArrayList<>(item) );
        
        createSubset( i+1, nums, prefix );
    }
}
*/



/*  
    version 3:  iterative
    按照subset的大小从size=1,逐渐增大到size=3
    过程： [1] [2] [3] -> [1,2] [1,3] [2,3] -> [1,2,3] + [ ]

*/
/*
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        res.add( new ArrayList<>() );
        if( nums == null || nums.length == 0 )
            return res;
        
        Queue<List<Integer>> idxQ = new LinkedList<>();
        int len = nums.length;
        
        for( int i = 0; i < len; i++ ){
            List<Integer> prefix = new ArrayList<>();
            prefix.add(i);
            idxQ.offer(prefix);
        }
            
        
        while( !idxQ.isEmpty() ){
            List<Integer> prefix = idxQ.poll();
            List<Integer> subset = new ArrayList<>();
            for( int i : prefix ){
                subset.add(nums[i]);
            }
            res.add( subset );
        
            int tempEndIdx = prefix.get(prefix.size()-1);
            for( int i = tempEndIdx+1 ; i < len; i++ ){
                List<Integer> newPrefix = new ArrayList<>(prefix);
                newPrefix.add(i);
                idxQ.offer(newPrefix);
            }
        }
        return res;
    }
}
*/