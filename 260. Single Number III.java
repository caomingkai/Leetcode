class Solution {
    public int[] singleNumber(int[] nums) {
        
        int[] res = new int[2];
        
        int allXor = 0;
        int n = nums.length;
        for( int i = 0; i < n; i++ ){
            allXor ^= nums[i];
        }
        
        int bit = 1;
        for( int i = 0; i < 32; i++ ){
            bit = bit << i ;
            if( ( allXor & bit ) != 0 )
                break;
        }
        
        int[] index1 = new int[n];
        int[] index2 = new int[n];
        int j = 0, k = 0;
        for( int i = 0; i < n; i++ ){
            if( ( bit & nums[i] ) != 0 )
                index1[j++] = i;
            else
                index2[k++] = i;
        }
        

        res[0] = SingleNumEasy(j, index1, nums);
        res[1] = SingleNumEasy(k, index2, nums);
        
        return res;
    }
    
    
    private int SingleNumEasy( int end, int[] index, int[] nums ) {
        int xor = 0;
        for( int i = 0; i < end; i++ ){
            xor ^= nums[index[i]];
        }
        return xor;
    }
}