import java.util.*;
import java.io.*;

class TwoSum{
	public static void main( String[] args ){
		int[] list = { 2, 1, 2, 3 , 4, 5, 6, 7 ,3, 2,4, 4,6, 3,2};
		int target = 7;

		System.out.println( findPairs(list, target) );
	}

	
	// version 1:  O(n) with HashMap
	private static List<List<Integer>> findPairs( int[] list, int t ){
		List<List<Integer>> res = new ArrayList<>();
		if( list == null || list.length < 2 )
			return res ;

		int len = list.length;
		Map<Integer, Integer> freq = new HashMap<>();

		for( int i = 0; i < len; i++ ){
			int buddy = t - list[i];
			if( freq.containsKey(buddy) ){
				int cnt = freq.get(buddy);
				List<Integer> pair = new ArrayList<>();
				pair.add(buddy);
				pair.add(list[i]);
				res.add(pair);
				if( cnt == 1 ) freq.remove(buddy);
				else freq.put(buddy, cnt-1 );
			}else{
				freq.put(list[i], freq.getOrDefault(list[i],0)+1 );
			}
		}
		return res;
	}
	

	/* 
	// version 2:  O(nlogn) with Two Pointer on sorted array
	private static List<List<Integer>> findPairs( int[] list, int t ){
		List<List<Integer>> res = new ArrayList<>();
		if( list == null || list.length < 2 )
			return res ;

		Arrays.sort(list);
		int len = list.length;
		int s = 0;
		int e = len-1;
		for( int i : list )
			System.out.println(i);

		while( s < e ){
			int sum = list[s] + list[e];
			if( sum > t )
				e--;
			else if( sum < t )
				s++;
			else{
				List<Integer> pair = new ArrayList<>();
				pair.add(list[s]);
				pair.add(list[e]);
				res.add(pair);
				s++;
				e--;
			}
		}
		return res;
	}
	*/

}

