/*
[6 7 8 9 0 1 2 3 4 5   ]

*/

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


// version 2:  1 - find out the MinIndex
//             2 - compare target with the last element 
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