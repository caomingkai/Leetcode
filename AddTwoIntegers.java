public class AddTwoIntegers{

	public static void main( String[] argv){

		int a = 5;
		int b = 1;
		System.out.println( addTwoIntegers(a, b));
	}

	private static int addTwoIntegers(int a, int b){
		int carry = a & b;
	    int res = a ^ b;
	    while( carry != 0 ){
	    	int tempCarry = carry << 1;
	    	int tempRes = res ^ tempCarry;    // after shifting carry bit, add it to current res excluding same bits
	    	carry = res & tempCarry;  // after shifting carry bit, check if still exist carry bit
	    	res = tempRes;
	    }
	    return res;
	}
}
