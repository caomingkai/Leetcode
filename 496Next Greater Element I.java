
// version 1: HashMap
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        
        int l1 = findNums.length;
        int[] r = new int[l1];
        int l2 = nums.length;
        
        // store index as 'value'
        Map<Integer, Integer> index = new HashMap();
        for( int i = 0; i < l2; i++){
            index.put( nums[i], i );
        }
        
        // find the first larger item
        for( int i = 0; i < l1; i++){
            
            int j = index.get( findNums[i] ) + 1;
            while( j < l2 && nums[j] <= findNums[i] ){
                j++;
            }
            if(j >= l2){
                r[i] = -1;
            }else{
                r[i] = nums[j];
            }
        }
        
        return r;
    }
}


/*
// version 2:  HashMap + Stack
public class Solution {
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
    
        Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            while (!stack.isEmpty() && stack.peek() < num)
                map.put(stack.pop(), num);
           stack.push(num);
        }   
        for (int i = 0; i < findNums.length; i++){
          findNums[i] = map.getOrDefault(findNums[i], -1);
        }
        return findNums;    
    }
}
*/