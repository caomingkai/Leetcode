class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = words.length;
        
        if( word1.equals(word2) ){
            int pre = -1, cur = -1;
            for (int i = 0; i < words.length; i++) {
                if( words[i].equals(word1) ){
                    pre = cur;
                    cur = i;
                    if (pre != -1 ){
                        minDistance = Math.min(minDistance, Math.abs(cur - pre));
                    }
                }
            }
            return minDistance;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                i1 = i;
            } else if (words[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }
        return minDistance;
    }
}