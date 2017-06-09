/*
public class Solution {
    public int romanToInt(String s) {
        int[] d = new int[26];
        d['I' - 'A'] = 1;
        d['V' - 'A'] = 5;
        d['X' - 'A'] = 10;
        d['L' - 'A'] = 50;
        d['C' - 'A'] = 100;
        d['D' - 'A'] = 500;
        d['M' - 'A'] = 1000;
        
        int sum = 0;
        int i = 0;
        int l = s.length();
        while( i < l-1 ){
            int one = d[ s.charAt(i) - 'A'];
            int two = d[ s.charAt(i+1) - 'A'];
            sum += one < two ? -one : one;
            i++;
        }
        sum += d[ s.charAt(i) - 'A'];
        return sum;
    }
}
*/

// version 2:
// the minus operations is only limited to 6 cases as follows
public class Solution {
public int romanToInt(String s) {
     int sum=0;
    if(s.indexOf("IV")!=-1){sum-=2;}
    if(s.indexOf("IX")!=-1){sum-=2;}
    if(s.indexOf("XL")!=-1){sum-=20;}
    if(s.indexOf("XC")!=-1){sum-=20;}
    if(s.indexOf("CD")!=-1){sum-=200;}
    if(s.indexOf("CM")!=-1){sum-=200;}
    
    char c[]=s.toCharArray();
    int count=0;
    
   for(;count<=s.length()-1;count++){
       if(c[count]=='M') sum+=1000;
       if(c[count]=='D') sum+=500;
       if(c[count]=='C') sum+=100;
       if(c[count]=='L') sum+=50;
       if(c[count]=='X') sum+=10;
       if(c[count]=='V') sum+=5;
       if(c[count]=='I') sum+=1;
   }
   return sum;
}
}