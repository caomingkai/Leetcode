/*
// didn't cover all corner cases
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if( nums == null  )
            return false;
        int l = nums.length;
        if( l == 1 && nums[0] == k )
            return true;
        if( l == 1 && nums[0] != k )
            return false;
        

        int[] sum = new int[l+1];
        sum[0] = 0;
        for( int i = 1; i <= l; i++ ){
            sum[i] = sum[i-1]+nums[i-1];
        }
        
        int f = 1, s = 0;
        while( f <= l ){
            if( s > f) return false;
            
            if( sum[f]-sum[s] == k )
                return true;
            else if( sum[f]-sum[s] < k)
                f++;
            else
                s++;
        }
        
        return false;
    }
}

*/

/*
// version 1: Cumulative sum
public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++)
            sum[i] = sum[i - 1] + nums[i];
        for (int start = 0; start < nums.length - 1; start++) {
            for (int end = start + 1; end < nums.length; end++) {
                int summ = sum[end] - sum[start] + nums[start];
                if (summ == k || (k != 0 && summ % k == 0))
                    return true;
            }
        }
        return false;
    }
}

*/
// version 2: HashMap
public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0)
                sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else
                map.put(sum, i);
        }
        return false;
    }
} 
