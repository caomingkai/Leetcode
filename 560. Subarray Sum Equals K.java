/*
    写在前面： 本题不能使用 “双指针-sliding window ” ！
             - 因为双指针在该类问题中要求： 前指针移动-->window sum增加； 尾指针移动->window sum减小；
             - 然而，这里由负数的存在；如果是正整数双指针可以使用
*/

/*
// version 1: brutal force
class Solution {
    public int subarraySum(int[] nums, int k) {
        
        int res = 0;
        if( nums == null || nums.length == 0 )
            return res;
        
        int l = nums.length;
        for( int i = 0; i < l; i++ ){
            if( nums[i] == k ) res++;
            
            int sum = nums[i];
            for( int j = i+1; j < l; j++ ){
                sum += nums[j];
                if( sum == k ) 
                    res++;
            }
        }
        return res;
    }
}
*/

// version 2: hashmap
class Solution {
    public int subarraySum(int[] nums, int k) {
        
        int res = 0;
        if( nums == null || nums.length == 0 )
            return res;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int l = nums.length;
        int sum = 0;
        
        // key: val <==>  sum : freq
        map.put(0, 1);
        for( int i = 0; i < l; i++ ){
            sum += nums[i];
            if( map.containsKey(sum-k) )
                res += map.get(sum-k);
            map.put( sum, map.getOrDefault(sum,0)+1 );
        }
        return res;
    }
}
