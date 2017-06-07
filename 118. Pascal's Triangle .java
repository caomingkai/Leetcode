/*
// version 1: no need to be so complicated
// ****drawbacks****
//           1. edge cases numRows =1 / 2, could fall into general case
//           2. no need maintain two List 'arrPre' and 'arrCur', only one is enough
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if( numRows == 0 ) return res;
        List<Integer> r1 = new ArrayList<Integer>();
        List<Integer> r2 = new ArrayList<Integer>();
        r1.add(1); 
        r2.add(1); r2.add(1);
        
        res.add(r1);
        if( numRows == 1 ) return res;
        res.add(r2);
        if( numRows == 2 ) return res;
        
        List<Integer> arrPre = r2;                // previous array
        for( int i = 3; i <= numRows; i++){
            int colCur = arrPre.size() + 1; // length for the current array
            List<Integer> arrCur = new ArrayList<Integer>();
            arrCur.add(1);        // 1st element
            for( int j = 1; j < colCur -1 ; j++){
                arrCur.add( arrPre.get(j-1) + arrPre.get(j) );
            }
            arrCur.add(1);
            res.add(arrCur);
            arrPre = arrCur;
        }
        return res;
    }
}
*/

// version 2
// ArrayList:
// 1 -- add(E e) : append to the END of the list
// 2 -- add(int index, E element) : Inserts the specified element at the specified position in this list
// 3 -- set(int index, E element) : Updates the specified position with new element

// key idea:  current is [1,3,3,1], then insert an extra '1' in front, now it is [1,1,3,3,1]
// from index = 1 to index = last element, arr[i] = arr[i] + arr[i+1], so now is [1,4,6,4,1], the last '1' leave it as it is.
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> arr = new ArrayList<Integer>();
        
        if( numRows < 0 ) return res;
        
        for( int i = 0; i < numRows; i++ ){
            arr.add( 0, 1);  // insert an extra '1' in front of the arr, NOT set(0,1), but INSERT
            for( int j = 1; j < row.size()-1; j++ ){
                arr.set(j, arr.get(j) + arr.get(j+1) );
            }
            res.add(arr);
        }
        return res;
    }
}