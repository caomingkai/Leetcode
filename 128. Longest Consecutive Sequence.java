class Solution {
    public int longestConsecutive(int[] nums) {
        
        // edge case
        if( nums == null || nums.length == 0 ) return 0;
        
        // general case
        int res = 1;
        HashSet<Integer> s = new HashSet<>();
        for( int i : nums ) 
            s.add(i);
        
        for( int item: nums ){
            if( s.contains( item-1 ) ){ 
                // no need to start from item, start counting from item-1 when we meet it as loop proceed
                // this step will ensure that we only start counting from the smallest number for sequences
                continue;
            }else{
                int cnt = 1;
                while( s.contains(item+1) ){
                    item++;
                    cnt++; 
                }
                res = Math.max(res, cnt);
            }
        }
        
        return res;
    }
}