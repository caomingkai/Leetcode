/*

    {1, 2, 3}
    
      nth  pos: rand(n) --> swap rand(n) with last item
                1/n   : 任意元素被选中的概率
                
     (n-1) pos: rand(n-1)  --> swap rand(n-1) with n-1 item
                (n-1)/n    *   1/n-1 = 1/n    ： 任一元素前几轮未被选中的概率 * 本轮被选中的概率
                上一轮未选中    本轮选中
                
     (n-2) pos: rand(n-1)  --> swap rand(n-1) with n-1 item
                (n-1)/n  * (n-2)/(n-1)   *  1/n-2 = 1/n ： 任一元素前几轮未被选中的概率 * 本轮被选中的概率
                   上两轮都未选中            本轮选中
    
*/

// version 1: Fisher-Yates
// time: O(n)
// space: O(n)

class Solution {
    
    private int[] originArr;
    private Random rand = new Random();
    
    public Solution(int[] nums) {
        int l = nums.length;
        originArr = new int[l];
        for( int i = 0; i < l; i++ )
            originArr[i] = nums[i];
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return originArr;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int l = originArr.length;
        int[] randomArr = Arrays.copyOf( originArr, l );
        
        for( int i = l-1; i >= 0; i-- ){
            int randPos = rand.nextInt(i+1); // random position from [0, i+1)
            int temp = randomArr[randPos];
            randomArr[randPos] = randomArr[i];
            randomArr[i] = temp;
        }
        return randomArr;
    }
}

/*
// version 2:  
// 1--- put all number in a 'hat'
// 2--- draw a num per time

// proof:
//  1st pos: 1/n
//  2nd pos: (n-1)/n * 1/(n-1) = 1/n     上几轮未选中的概率*本轮被选中的概率
//  3rd pos: (    (n-1)/n  *  (n-2)/(n-1) ) * 1/(n-2)    = 1/n 
//                1st未选中 &&   2nd未选中   &&   3rd被选中


// time: O(n^2)
// space: O(n)

class Solution {
    private int[] array;
    private int[] original;

    private Random rand = new Random();

    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }

    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }
    
    public int[] reset() {
        array = original;  
        original = original.clone(); // have to create a new one! since reference given to array
        return array;
    }
    
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }
}

*/