// loop from array's rear to head has an advantage:
// if is i,j both match the loop condition, and still i > 0
// we can see the left element from i is surely small,
// THERE IS NO NEED TO MOVE ELEMENT IN NUMS1

import java.util.Arrays;

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1, j=n-1, k=m+n-1;
        while( i > -1 && j > -1){   
            if(  nums1[i] > nums2[j] ){
                nums1[k] = nums1[i];
                i--;
                k--;
            }else{
                nums1[k] = nums2[j];
                j--;
                k--;
            }
        }
        while(j > -1){
            nums1[k] = nums2[j];
            j--;
            k--;
        }
        
    }
}
