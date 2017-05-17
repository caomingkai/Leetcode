/*
* 1st: sort first, then count different value
* 2nd: loop through, insertion sort
* 3rd: HashSet
*
*/

public class Solution {
    public int distributeCandies(int[] candies) {
        // 1st way:
        /*
        Arrays.sort( candies );
        
        int len = candies.length;
        int count =  1;
        int prev = candies[0];
        int i = 1;
        
        while( i < len ){
            int next = candies[i];
            if( next != prev ){
                count++;
                prev = next;
            }
            i++;
        }
        
        if( count >= len/2){
            return len/2;
        }
        
        return count;
        */
        
        // 3rd way:
        
        int len = candies.length;
        
        Set<Integer> candySet = new HashSet<Integer>();
        for( int i = 0; i < len; i++){
            candySet.add( candies[i] );
        }
        
        return candySet.size() > len/2 ? len/2 : candySet.size();
        
        
    }
}