// version 0: bit manipulation
// KEY POINT : a ^ b ^ b == a 
public int missingNumber(int[] nums) {

    int xor = 0, i = 0;
	for (i = 0; i < nums.length; i++) {
		xor = xor ^ i ^ nums[i];
	}

	return xor ^ i;
}

/*
// version 1: sum difference
public class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int num: nums)
            sum += num;
            
        return (nums.length * (nums.length + 1) )/ 2 - sum;
    }
}
*/


/*
// version 2: one by one comparison
public class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int l = nums.length;
        int i = 0;
        for(  i = 0; i < l; i++){
            if( nums[i] != i )return i;
        }
        return i;
    }
}


// version 3: binary search
public int missingNumber(int[] nums) { //binary search
    Arrays.sort(nums);
    int left = 0, right = nums.length, mid= (left + right)/2;
    while(left<right){
        mid = (left + right)/2;
        if(nums[mid]>mid) right = mid;
        else left = mid+1;
    }
    return left;
}

*/

