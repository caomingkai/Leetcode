public class Solution {
	private static final String[] ones= {" zero"," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"};
	private static final String[] teens= {" Ten"," Eleven"," Twelve"," Thirteen"," Fourteen"," Fifteen"," Sixteen"," Seventeen"," Eighteen"," Nineteen"};
	private static final String[] tens= {" zero"," zero"," Twenty"," Thirty"," Forty"," Fifty"," Sixty"," Seventy"," Eighty"," Ninety"};

    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String res = i2e(num); 
        return res.trim();
    }
    
    private String i2e(int n) {
        String res = "";
		if( n >= 1000000000 ){
			res += i2e(n/1000000000 ) + " Billion" + i2e(n%1000000000);
		}else if( n >= 1000000 ){
			res += i2e(n/1000000 ) + " Million"  + i2e(n%1000000);
		}else if( n >= 1000 ){
			res += i2e(n/1000 ) + " Thousand" + i2e(n%1000);
		}else if( n >= 100 ){
			res += ones[n/100] + " Hundred" + i2e(n%100);
		}else if( n >= 20 ){
			res += tens[n/10] +i2e(n%10);
		}else if( n >= 10 ){
			res += teens[n-10];
		}else if( n > 0 ){
			res += ones[n];
		}
		return res;
    }
}