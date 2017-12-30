/*
// version 1: stack-overflow
class Solution {
    public int jump(int[] nums) {
        
        int l = nums.length;
        
        int[] minStep = new int[l];
        
        minStep[l-1] = 0;
        
        for( int i = l-2; i >= 0; i-- ){
            if( i + nums[i] >= l-1 )
                minStep[i] = 1;
            else{
                int curMin = Integer.MAX_VALUE;
                for( int j = 1; j <= nums[i]; j++ ){
                    curMin = curMin < minStep[i+j] ? curMin : minStep[i+j];
                }

                if( curMin == Integer.MAX_VALUE )
                    minStep[i] = Integer.MAX_VALUE;
                else
                    minStep[i] = curMin +1;
            }
        }
        
        return minStep[0];
    }
}
*/

/*
// version 2: BFS - shortest path using BFS
// BFS - find level - version 2
class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int steps = 0, tmpMax = 0, i = 0;
        while (tmpMax < len - 1) {
            int localTmp = tmpMax;
            for (; i <= localTmp; i++) {
                tmpMax = Math.max(tmpMax, i + nums[i]);
            }
            steps++;
        }
        return steps;
    }
}
*/

// version 3: Two Pointer - maintain a range: [start , end]
class Solution {
    public int jump(int[] nums) {

        if( nums.length == 1 )
            return 0;
        
        int res = 0;
        int start = 0;
        int end = 0;
        int i = 0;
        
        while( end < nums.length ){
            
            res++;

            int nextEnd = 0;
            for( i = start; i <= end; i++ ){
                nextEnd = nextEnd > nums[i]+i ? nextEnd : nums[i]+i;
            }
            
            start = end+1;
            end = nextEnd;
            if( start > end )
                return Integer.MAX_VALUE;
            
            if( nextEnd >= nums.length-1 )
                return res;
            
        }
        
        return Integer.MAX_VALUE;
    }
}


/*
// BFS - find level - shortest path using BFS;
class Solution{
    public int jump(int[] nums) {
        
        if( nums.length == 1 )
            return 0;
        
        int curMaxIndex = 0;
        int nextMaxIndex = 0;
        int i = 0;
        int level = 0;
        
        while( i <= curMaxIndex ){
            
            level++;
            
            for( ; i <= curMaxIndex; i++ ){
                nextMaxIndex = nextMaxIndex > nums[i]+i ? nextMaxIndex : nums[i]+i;
            }
            
            if( nextMaxIndex >= nums.length-1 )
                return level;
            
            curMaxIndex = nextMaxIndex;
        }
        
        return Integer.MAX_VALUE;
    }
    
}
*/


//