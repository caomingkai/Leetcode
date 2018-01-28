class Solution {
    public int lengthOfLIS(int[] nums) {
        if( nums == null || nums.length == 0 )
            return 0;
        
        int res = 1;
        int len = nums.length;
        int[] dp = new int[len]; // dp[i] : starting with ith item till last item, the len of LIS
        dp[len-1] = 1;
        
        for( int i = len-2; i >= 0; i-- ){
            int maxLen = 0;
            for( int j = i+1; j < len; j++ ){
                if( nums[j] > nums[i] )
                    maxLen = Math.max(maxLen, dp[j] );
            }
            dp[i] = maxLen + 1;
            res = Math.max( dp[i], res);
        }
        
        return res;
    }
}



/*
在1,3,5,2,8,4,6这个例子中，当到6时，我们一共可以有四种
(1)不同长度
(2)且保证该升序序列在同长度升序序列中末尾最小
的升序序列

1
1,2
1,3,4
1,3,5,6
这些序列都是未来有可能成为最长序列的候选人。这样，每来一个新的数，我们便按照以下规则更新这些序列

如果nums[i]比所有序列的末尾都大，或等于最大末尾，说明有一个新的不同长度序列产生，我们把最长的序列复制一个，并加上这个nums[i]。

如果nums[i]比所有序列的末尾都小，说明长度为1的序列可以更新了，更新为这个更小的末尾。

如果在中间，则更新那个末尾数字刚刚大于等于自己的那个序列，说明那个长度的序列可以更新了。
*/
/*
// version 2: Greedy  -->  O(nlogn)
public class Solution   
{  
    public int LengthOfLIS(int[] nums)   
    {  
        int n = nums.Length;  
        if (n == 0)  
            return 0;  
        // len表示当前最长的升序序列长度（为了方便操作tails我们减1）  
        int len = 0;  
        // tails[i]表示长度为i的升序序列其末尾的数字  
        int[] tails = new int[n];  
        tails[0] = nums[0];  
        // 根据三种情况更新不同升序序列的集合  
        for (int i = 1; i < n; i++)  
        {  
            if (nums[i] < tails[0])  
                tails[0] = nums[i];  
            else if (nums[i] > tails[len])  
                tails[++len] = nums[i];  
            else  
                // 如果在中间，则二分搜索  
                tails[binarySearch(tails, 0, len, nums[i])] = nums[i];  
        }  
        return len + 1;  
    }  
      
    private int binarySearch(int[] tails, int min, int max, int target)  
    {  
        while (min <= max)  
        {  
            int mid = (max + min) / 2;  
            if (tails[mid] == target)  
                return mid;  
            if (tails[mid] < target)  
                min = mid + 1;  
            if (tails[mid] > target)  
                max = mid - 1;  
        }  
        return min;  
    }  
}  

*/