

class Solution {
    /*
    // version 1: 最普通的做法： 先检查bull，后检查cow
    public String getHint(String secret, String guess) {
        int A = 0;
        int B = 0;
        
        char[] charS = secret.toCharArray();
        char[] charG = guess.toCharArray();
        
        int[] count = new int[10];   // map digit to its frequency
        for( int i = 0; i < charS.length; i++ )
            count[charS[i]-'0']++;
        
        int i = 0;
        while( i < charS.length ){
            if( charS[i] == charG[i] ){
                count[charS[i]-'0']--;
                A++;
            }
            i++;
        }
        
        for( i = 0; i < charS.length; i++ ){
            if( charS[i] != charG[i] ){
                if( count[charG[i]-'0'] > 0 ){
                    B++;
                    count[charG[i]-'0']--;
                }
            }
        }
        
        return A + "A" + B + "B";
    }
    */
    
    /*
    // version 2: 
                    - 用两个数组分别保存“非bull”数字分别在两个list中出现的次数
                    - 二者中较小的那个数，表明两个list中该digit都出现过的次数
    public String getHint(String secret, String guess) {
        int[] a1 = new int[256];
        int[] a2 = new int[256];

        int count1 = 0, count2 = 0;

        for(int i = 0; i < secret.length(); i++){
            if(secret.charAt(i) == guess.charAt(i)) count1++;
            else{
                a1[secret.charAt(i)]++;
                a2[guess.charAt(i)]++;
            }
        }

        for(int i = 0; i < 255; i++){
            if(a1[i] == a2[i]) count2+=a1[i];
            else count2 += Math.min(a1[i],a2[i]);
        }

        return count1+"A"+count2+"B";
    }

    */
    
    
    
    /*
        version 3: 【优化】 两个Array --> 一个Array
                    - 如果secret出现一次某个数字，该位置就自增1，如果此时该位置小于0，
                        就说明这个数字在guess出现过()，因此cows就自增1；
                    - guess的处理和secret类似，区别就是index对应元素需要自减。
    */                

    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() != guess.length()) return "0A0B";
        int bullsCount = 0, cowsCount = 0;
        int len = secret.length();
        int[] count = new int[10];
        
        for (int i = 0; i < len; i++) {
            int sc = secret.charAt(i) - '0';
            int gc = guess.charAt(i) - '0';
            if (sc == gc) {
                bullsCount++;
            } else {
                if (count[gc] > 0) cowsCount++; //对guess而言，负责减count；若此时count>0,表明secret也有该数字
                if (count[sc] < 0) cowsCount++; //对secret而言，负责加count；若此时count<0,表明guess也有该数字
                count[sc]++;
                count[gc]--;
            }
        }
        return bullsCount + "A" + cowsCount + "B";
    }

     
}