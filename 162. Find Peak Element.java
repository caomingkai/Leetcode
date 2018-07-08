class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n-1;
        
        /*   1.  先确定左右l、r 的取值情况, 决定 m的计算方式、以及while循环结束条件
             2.  根据l、r取值情况，决定m往大里取值or往小里取值。
                 r、l中任何一个若更新为m，那么它有可能导致“死循环”，需要在下次计算m的时候，避免 m 再次落到它上面，即
                   - 若 l=m, r=m-1, 则 m=(r-l)/2 + l + 1
                   - 若 l=m+1, r=m, 则 m=(r-l)/2 + l
             3.  根据l、r取值情况，确定循环结束条件
                   - 若l、r有任何一个为：l=m 或者 r=m，那么终结条件为最后还剩2个元素： while(l<r)
                   - 若l、r更新值都不等于m： l=m+1 && r=m-1，那么终结条件为最后剩1个元素： while(l<=r)
             4.  决定返回 l或r，取决于 第2条，那个更新为m的 l 或者 r
        */
        while( l < r ){
            int m = (r-l)/2 + l + 1;
            if( nums[m] < nums[m-1] )
                r = m-1;
            else
                l = m;
        }
        return l;
    }
}

