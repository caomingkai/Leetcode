

class Solution {
    public int search(int[] nums, int target) {
        if( nums == null || nums.length == 0 )
            return -1;
        
        int minIdx = findMinIdx( nums );
        int n = nums.length;
        if( target == nums[n-1] ) 
            return n-1;
        int l = target > nums[n-1] ? 0 : minIdx;
        int r = target > nums[n-1] ? minIdx-1 : n-1;
        
        while( l <= r ){
            int m = l + ((r-l)>>1);
            if( nums[m] == target )
                return m;
            else if( nums[m] > target )
                r = m-1;
            else 
                l = m+1;
        }
        return -1;
        
    }
    
    
        //     0 1 2 3 4 5 6 7
        //   [ 5 6 7 8 9 0 1 2  ]
    //        l   r   m
    //        0   7   3  =8 > 5  l = m+1=4
    //        4   7   5  =0 < 9  r = m = 5
    //        4   5   4  =9 = 9  r = m = 4
    //        4   4   4  

    /*
    nums[m] < nums[r]    r = m
    nums[m] > nums[r]    l = m+1
    cannot exist nums[m] == nums[r], since we round down to an integer
    if we round-down, it could be nums[m] == nums[l], but never nums[m] == nums[r]
    */
    
    private int findMinIdx( int[] nums){
        int l = 0;
        int r = nums.length - 1;
        while( l < r ){
            int m = l + (( r - l) >> 1);   // when only 2 elements left, the left one will be min
            if( nums[m] < nums[r] )
                r = m;
            else
                l = m+1;    
        }
        return l;
    }
    
}


/*
// version 1:  nums[s] < nums[m] can determine front/back is sorted
class Solution {
    public int search(int[] nums, int target) {
        if( nums == null || nums.length == 0 )
            return -1;
        
        int l = nums.length;
        int s = 0;
        int e = l-1;
        while( s <= e ){
            int m = (s + e)/2;
            if(nums[s] < nums[m]){           // front part is sorted
                if( target > nums[m] ){      // located in back part
                    s = m+1;
                }else if( target < nums[m] ) {
                    if( target < nums[s] )   // located in back part
                        s = m+1;
                    else                     // located in front part
                        e = m-1;  
                }else
                    return m;
             }else if(nums[s]> nums[m]){    //  back part is sorted
                if( target > nums[m] ){
                    if( target > nums[e] )   // located in front part
                        e = m-1;
                    else                     // located in back part
                        s = m+1;
                }else if(target < nums[m]){  // located in front part
                    e = m-1;
                }else
                    return m;
             }else {                         // only two elements left
                 if( target == nums[s] ) return s;
                 if( target == nums[e] ) return e;
                 s = m+1;
             }
        }
        return -1;
    }
}

*/

/*
// version 2:  1 - find out the MinIndex
//             2 - compare target with the last element 

// KEY IDEA: 
// 1. first compare target with breaking point
// 2. if larger than breaking point, located in first half part
//    if smaller than breaking point, located in second half part

class Solution {
    public int search(int[] nums, int target) {
        
        if( nums == null || nums.length == 0 )
            return -1;
        
        int minIdx = findMinIdx(nums);
        if (target == nums[minIdx]) return minIdx;
        int m = nums.length;
        int start = (target <= nums[m - 1]) ? minIdx : 0;
        int end  =  (target <= nums[m - 1]) ? m - 1  : minIdx-1 ;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (target > nums[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }
    
    //   [ 5 6 7 8 9 0 1 2 3 ]
    public int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end -  start) / 2;
            if (nums[mid] > nums[end]) start = mid + 1;
            else end = mid;
        }
        return start;
    }
}
*/