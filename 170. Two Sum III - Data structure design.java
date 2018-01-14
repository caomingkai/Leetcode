

/*

/
// version 1: put more calculation on add()
class TwoSum {
    // [1, 3, 5, 6, 7]
    // [4, 6, 8, 7, 9, 11]
    Set<Integer> set = new HashSet<>();
    List<Integer> list = new ArrayList<>();

    public TwoSum() {
    }
    
    public void add(int number) {
        for( Integer item: list )
            set.add( item + number );
        list.add(number);
    }
    
    public boolean find(int value) {
        return set.contains(value);
    }
}
*/




/*
// version 2:  put more calculation on find()
//             HashMap fastest!
public class TwoSum {
    private List<Integer> list = new ArrayList<Integer>();
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    // Add the number to an internal data structure.
	public void add(int number) {
	    if (map.containsKey(number)) map.put(number, map.get(number) + 1);
	    else {
	        map.put(number, 1);
	        list.add(number);
	    }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (int i = 0; i < list.size(); i++){
	        int num1 = list.get(i), num2 = value - num1;
	        if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) return true;
	    }
	    return false;
	}
}

*/




// version 3: put more calculation on find()
//            HashSet
//            two pointer
class TwoSum {
    // [1 ,1 ,5 ,4 7]
    //  2
    //  1: 1
    //  5: 3
    //  
    
    List<Integer> list = new ArrayList<>();
    public TwoSum() {
    }
    
    public void add(int number) {
        list.add(number);
    }
    
    public boolean find(int value) {
        /*
        //---- HashSet version 
        Set<Integer> set = new HashSet<>();
        for( Integer item: list ){
            
            if( set.contains(item) )
                return true;
            else
                set.add( value - item );
        }
        return false;
        */
        
        //-----  two pointers
        Collections.sort(list);
        int f = 0;
        int b = list.size() - 1;
        while( f < b ){
            int sum = list.get(f) + list.get(b);
            if( sum == value )
                return true;
            else if( sum < value )
                f++;
            else
                b--;
        }
        return false;
        
    }
}




/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */