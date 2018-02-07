/*
// version 1: HashMap
public class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> dict = new HashMap<>();
        int l = nums.length;
        for( int i = 0; i < l; i++)
        {
            dict.put( nums[i], dict.getOrDefault(nums[i],0) + 1 );
        }
        
        int m = nums[0];
        int r = 0;
        for( int key : dict.keySet() )
        {
            int v = dict.get(key);
            if(v > l/2 ){
                return key;
            }
        }
        return r;
    }
}
*/

/*
// version 2: Moore Voting Algorithm
// 核心思想： + “冲票！” 将选举各方的票相抵消，最后剩下来的必将是多数派！
//          +  将任意两个不相同的元素消除掉，直到数组中只有一种元素

public class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 1;
        int pointer = nums[0];
        
        int l = nums.length;
        for( int i = 1; i < l; i++ ){
            if( cnt == 0 ){
                pointer = nums[i];
                cnt = 1;
            }else{
                if( pointer == nums[i] ) 
                    cnt++;
                else
                    cnt--;
            } 
        }
        return pointer;
    }
}
*/

/*
// version 3:  概率求解： 
//             1. 随便从数组中选择一个元素，肯定有 p>0.5 的概率选中 majority element
//             2. 剩下的就是验证，如果不是的话，重新选择
public class Solution {
    public int majorityElement(int[] nums) {
        
        int res = 0;
        int len = nums.length;
        Random r = new Random();
        
        while( true ){
            int idx = r.nextInt(len);
            int target = nums[idx];
            
            int cnt = 0;
            for( int i = 0; i < len; i++ ){
                if( target == nums[i] ) cnt++;
            }
            if( cnt > len/2 ){
                res = target;
                break;
            }
        }
        return res;
    }  
}
*/


// version 4: bit manipulation
// 将每个元素转化为二进制数，从最后一位开始，比较0多还是1多，
// 假设0多，就把剩下的末尾为1的数字删掉，再在末尾为0的数字中进行第二次比较，
// 比较倒数第二位是0多还是1多，删掉该位置0或1较少的元素，以此类推。
public class Solution {
    public int majorityElement(int[] nums) {
        int[] bit = new int[32];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                bit[j] += (nums[i] >> j) & 1;   // bit[]中存的是，该bit位 二进制“1”的个数
            }
        }
        int majority = 0;
        for (int j = 0; j < 32; j++) {
            bit[j] = bit[j] > (nums.length / 2)? 1 : 0; // 该位“1”的数目>半数,则“1”，否则为“0”，
            majority += bit[j] << j;                    // 也就是majority number对应的位上的二进制
        }
        return majority;
    }
}