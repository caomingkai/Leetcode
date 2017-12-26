class Solution {
    /*
    // version 2: 还是利用位运算才有办法实现不用额外空间，就是遍历32次每次记录某位的出现的次数，如果不能被三整除，说明那个出现一次的就在该位有值，那么ans 或该位一下就可以了。
    public int singleNumber(int[] nums) {
        int ans = 0;
        int n = nums.length;
        
        for (int i = 0; i < 32; i++)
        {
            int cnt = 0, bit = 1 << i;
            for (int j = 0; j < n; j++)
            {
                if ( (nums[j] & bit) != 0 ) cnt++;
            }
            if (cnt % 3 != 0)
                ans |= bit;
        }
        return ans;
    }
    */
    
    // version 3:
    // 1. A new number appears - It gets XOR'd to the variable "ones".
    // 2. A number gets repeated(appears twice) - It is removed from "ones" and XOR'd to thevariable "twos".
    // 3. A number appears for the third time - It gets removed from both "ones" and "twos".
    public int singleNumber(int[] nums) {
        
        int ones = 0, twos = 0, xthrees = 0;
            
        int n = nums.length;
        for(int i = 0; i < n; ++i) {
            twos |= (ones & nums[i]);
            ones ^= nums[i];
            xthrees = ~(ones & twos);
            ones &= xthrees;
            twos &= xthrees;
        }

        return ones;
    }
}