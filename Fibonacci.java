import java.io.*;
import java.util.*;

class Fibonacci{
	public static void main( String[] args ){

		//  1 1 2 3 5 8 13
		int k = 3;
		System.out.println( getFibonacci(k) );
	}

	/*
	// iterative 
	private static int getFibonacci( int k ){
		if( k == 0 ) return 0;
		if( k == 1 || k == 2 ) return 1;
		
		int[] f = new int[k+1];
		f[1] = 1;
		f[2] = 1;
		for( int i = 3; i <= k; i++ ){
			f[i]=f[i-1]+f[i-2];
		}

		return f[k];
	}
	*/

	// recursive 
	static ArrayList<Integer> list = new ArrayList<>();
	static {
		list.add(0);
		list.add(1);
		list.add(1);
	}
	private static int getFibonacci( int k ){
		if( k <= 2 ) return list.get(k);

		if( k < list.size() )
			return list.get(k);

		int sum = getFibonacci(k-1) + getFibonacci(k-2);
		list.add(sum);

		return sum;
	}
}