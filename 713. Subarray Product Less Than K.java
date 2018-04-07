/**
 * The idea is always keep an max-product-window less than K;
 * Every time add a new number on the right(j), reduce numbers on the left(i), until the subarray product fit less than k again, (subarray could be empty);
 * Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
 * example:
 * for window (5, 2, 6), when 6 is introduced, it add 3 new subarray:
 *       (6)
 *    (2, 6)
 * (5, 2, 6)
 
 // 【关键】total += (j - i + 1); 
 // 每次窗口更新了之后，引入的增量为： 
 //   - 新增加的右端item本身(1)；
 //   - 以及窗口中其它值与该新增item组成的pair:(j-i)
 //   - ( j - i + i )
 */

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        long p = 1l;
        int i = 0;
        int j = 0;
        int total = 0;
        while(j < n){
            p *= nums[j];
            while(i <= j&&p >= k){
                p /= nums[i];
                i++;
            }
            total += (j - i + 1);
            j++;
        }
        return total;
    }
}

/*
// TLE
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if( nums == null || nums.length == 0 )
            return 0;
        int len = nums.length;
        int cnt = 0;
        
        for( int head = 0; head < len; head++ ){
            for( int tail = head; tail < len; tail++ ){
                int prod = 1;
                for( int i = head; i <= tail; i++ ){
                    prod *= nums[i];
                }
                if( prod < k ) cnt++;
                else break;
            }
        }
        return cnt;
    }
}
*/