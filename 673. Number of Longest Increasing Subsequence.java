
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if( nums == null || nums.length == 0 )
            return 0;

        int len = nums.length;
        int[] length = new int[len]; // length[i] : starting from 0 to ith item, the len of LIS
        int[] numLen = new int[len];
        length[0] = 1;
        numLen[0] = 1;
        
        for( int i = 1; i < len; i++ ){
            for( int j = 0; j < i; j++ ){
                if( nums[j] < nums[i] ){
                    if( length[j]+1 > length[i] ){
                        length[i] = length[j]+1;
                        numLen[i] = numLen[j];
                    }else if( length[j]+1 == length[i]){
                        numLen[i] += numLen[j];
                    }
                }
            }
            if( numLen[i] == 0 ){ // if all its predecessors larger than current element, set '1' to both
                numLen[i] = 1;
                length[i] = 1;
            }
        }
        
        int longest = 0;
        for( int i = 0; i < len; i++ ){
            System.out.println(length[i]);
            longest = longest > length[i] ? longest : length[i];
        }
                
        int res = 0;
        for( int i = 0; i < len; i++ ){
            if( length[i] == longest )
                res += numLen[i];
        }
            
        return res;
    } 

}

/*
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) return N;
        int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < j; ++i) if (nums[i] < nums[j]) {
                if (lengths[i] >= lengths[j]) {
                    lengths[j] = lengths[i] + 1;
                    counts[j] = counts[i];
                } else if (lengths[i] + 1 == lengths[j]) {
                    counts[j] += counts[i];
                }
            }
        }

        int longest = 0, ans = 0;
        for (int length: lengths) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < N; ++i) {
            if (lengths[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }
}
*/