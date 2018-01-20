
/*
// Version 1: O(n^3)

class Solution {
    public int triangleNumber(int[] nums) {
        if( nums == null || nums.length < 3 )
            return 0;
        
        int cnt = 0;
        int l = nums.length;
        Arrays.sort(nums);
        
        for( int i = 0; i < l; i++ ){
            for( int j = i+1; j < l; j++ ){    
                for( int k = j+1; k < l; k++ ){
                    if( isTriangle(nums[i],nums[j],nums[k]) )
                        cnt++;
                    else
                        break;
                }
            }
        }
        return cnt;
    }
    
    private boolean isTriangle( int i,int j,int k ){
        return i+j>k && i+k>j && k+j>i;
    }
}
*/


/*
// Version 2: O(n^2 logn)
// key idea:  since sorted ( a b c ) => a+c>b 与 b+c>a 肯定满足，只需要找到使a+b>c（即c<a+b）的c即可
class Solution {
    public int triangleNumber(int[] nums) {
        if( nums == null || nums.length < 3 )
            return 0;
        
        Arrays.sort(nums);
        int cnt = 0;
        int l = nums.length;
        for( int i = 0; i < l; i++ ){
            for( int j = i+1; j < l; j++ ){
                int target = nums[i] + nums[j];
                int left = j+1, right = l-1;
                if( left < l && nums[left] < target ){ // ensure there exist at least one valid triple
                    int endIdx = binarySearch( left, right, target, nums); // find the last item < target
                    cnt += endIdx-left+1;
                }
            }
        }
        return cnt;
    }
    
    
    //   1 2 3 4 5 6 6 6 8   target= 7
    //   n[m] >= target:  r = m-1
    //   n[m] < target:   l = m
    //   m = (l+r+1)/2
    //   return l
    // find out the last element whose value less than target
    private int binarySearch( int l, int r, int target, int[] nums){
        while( l < r){
            int m = l+(r-l+1)/2;
            if( nums[m] >= target )
                r = m-1;
            else
                l = m;
        }
        return l;
    }
}
*/


// version 3: make use of sorted array,  排序（Sort） + 双指针（Two Pointers）
// key idea: 
//          1. use a pointer p to scan from back to end
//          2. use two pointers[left=0, right=p-1] to find pairs n[l]+n[r]>n[p]
//          3. then the elements between [left,right] are all satify n[l]+n[r]>n[p]
class Solution {
    public int triangleNumber(int[] nums) {
        if( nums == null || nums.length < 3 )
            return 0;
        
        Arrays.sort(nums);
        int cnt = 0;
        int len = nums.length;
        
        for( int p = len-1; p >= 0; p-- ){
            int l = 0;
            int r = p-1;
            int startIdx = 0;
            if( r > 0 )
                cnt += count( l, r, nums[p], nums); 
        }
        return cnt;
    }
    
    
    // keep finding n[l]+n[r]>t
    private int count( int l, int r, int t, int[] nums){
        int cnt = 0;
        while( l < r ){
            int s = nums[l]+nums[r];
            if( s > t ){
                cnt += r-l ;
                r--;
            }else{
                l++;
            }
        }
        return cnt;
    }
}