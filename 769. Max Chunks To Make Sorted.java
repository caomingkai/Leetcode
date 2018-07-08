
// version 1:
// 找第一个chunk的时候：从开始对每个位置的元素进行检查，要形成一个valid的chunk，必须要求：
// 该chunk的每个元素的值 <= chunk的右边界位置，又因为求最多的chunk数目，因此:
// 贪心的要求该chunk中的max value == chunk 右边界位置
// 注意： 在满足了chunk右边界位置==max value后，同时也会满足小于该值的所有元素恰好填满左边的indices
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int ret = 0;
        if( arr == null || arr.length == 0 ) return ret;
        
        int left = 0, right = 0;
        int n = arr.length;
        int cur = arr[left];
        
        while( left < n ){
            int curMax = arr[left];
            for( int i=left; i<=curMax; i++ ){  // 循环体内“动态”更新循环终结条件
                if( arr[i] > curMax ) 
                    curMax = arr[i];
            }
            ret++;
            left = curMax+1;
        }
        return ret;
    }
}


/*
// version 2
class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[] max = new int[arr.length];
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }
        
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max[i] == i) {
                count++;
            }
        }
        
        return count;
    }
}
*/