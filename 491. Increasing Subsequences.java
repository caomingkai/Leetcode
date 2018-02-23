/*
    [4,6,7,7]
    
    对于每个prefix，在其之后附加的数字
    1. 只能从当前位置向后找（prefix最后一个数字的，index一块压入queue，考虑用两个queue同步：一个value、一个index）
    2. 不能重复，要唯一（附加数字之前检查Set中必须没有该值；附加之后将该值插入set）
    
    [ ]
    set [ 4 6 7 ]
    
    4 6 / 4 7 / 6 7 / 7 7 : 
    
    4 6 7 / 
    
*/
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        if( nums == null || nums.length == 0 ) return res;
        
        Queue<List<Integer>> prefixQ = new LinkedList<>();
        Queue<Integer> indexQ = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        
        for( int i = 0; i < nums.length; i++ ){
            if( set.contains(nums[i]) ) continue;
            else{
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                prefixQ.offer(list);
                indexQ.offer(i);
                set.add(nums[i]);
            }
        }
        
        while( !prefixQ.isEmpty() ){
            List<Integer> prefix = prefixQ.poll();
            int index = indexQ.poll();
            set.clear();
            for( int i = index+1; i < nums.length; i++ ){
                if( set.contains(nums[i]) ) continue;
                else{
                    if( nums[i] < nums[index]) continue;
                    List<Integer> list = new ArrayList<>(prefix);
                    list.add(nums[i]);
                    res.add(list);
                    prefixQ.offer(list);
                    indexQ.offer(i);
                    set.add(nums[i]);
                }
            }
        }
        
        return res;
    }
}