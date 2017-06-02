public class Solution {
    public int rob(int[] nums) {

        ArrayList<Integer> index = new ArrayList<Integer>(); // sort index descendingly based on their value
        int l = nums.length;
        for( int i = 0; i < l; i++){
            index.add(i);
        }
        // version 1:
        // index.sort( new Comparator<Integer>(){
        //     @Override
        //     public int compare( Integer i, Integer j){
        //         return nums[j] - nums[i];
        //     }
        // } );
        
        // version 2:
        // index.sort( (a,b) -> nums[b] - nums[a]  ); // lambda expression instead of Comparator above
        
        // version 3:
        index.sort( Comparator.comparing( (Integer p) -> nums[p] ).reversed() );
        for( int i = 0; i < l; i++){
            System.out.println( index.get(i) );
        }
        Set< Integer > s = new HashSet<>();
        int sum = 0;
        for( int i : index )
        {
            if( !s.contains(i) ){
                sum += nums[i];
                s.add( i - 1 );
                s.add( i + 1 );
            }
        }
        return sum;
    }
}