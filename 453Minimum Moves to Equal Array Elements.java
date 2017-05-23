// version 1: regular thinking
// denote 
// N        is number of moves
// n        is number of elements
// sum      is the sum of original array elements
// min      is the smallest element
// maxToBe  is the eventually identical elements
// we would have two equation as follows
// 1. sum + (n-1) * N = maxToBe * n
// 2. min + N = maxToBe
// so we could get the final equation
// sum + (n-1)*N = (min+N) * n
public class Solution {
    public int minMoves(int[] nums) {
        if( nums == null || nums.length == 0) return 0;
        // result = sum - min * n
        // namely (nums[0] - min) + (nums[1] - min) + ... + (nums[i] - min)
        int min = nums[0];
        for (int n : nums) min = Math.min(min, n);
        int res = 0;
        for (int n : nums) res += n - min;
        return res;
    }
}

/*
// Version 2 : reverse thinking( Even though bear the same code format, the ideas are different )

//Adding 1 to n - 1 elements is the same as subtracting 1 from one element, w.r.t goal of making the elements in the array equal
//So, best way to do this is make all the elements in the array equal to the min element.
//sum(array) - n * minimum

public class Solution {
    public int minMoves(int[] nums) {
        if (nums.length == 0) return 0;
        int min = nums[0];
        for (int n : nums) min = Math.min(min, n);
        int res = 0;
        for (int n : nums) res += n - min;
        return res;
    }
}
*/