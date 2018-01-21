/*
    0 1 2 3 4 5 6 7 8 9 10  11 12
    A A A A A C C C C C  A  A  A  A ACCCCCCAAAAAGGGTTT
    |---------10------|   -->

*/

/*
// version 1:  set<String>  for pattern record
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        
        Set<String> res = new HashSet<>();  // use set to avoid duplicate substring
        if( s == null || s.length() < 10 )
            return new ArrayList(res);
        
        Set<String> set = new HashSet<>();
        
        for( int i = 0; i <= s.length() - 10; i++ ){
            String substr= s.substring(i,i+10);
            if( set.contains(substr) )
                res.add(substr);
            else
                set.add(substr);
        }
        return new ArrayList(res);
    }
}

*/

// version 2: set<int> for pattern record, better than set<String>,
//            because: 32 bits can represent 10-character strings, 
//                                  which in turn use (10+1)*1 byte(8bits)= 88bits
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        
        Set<Integer> set = new HashSet<Integer>();
        Set<String> res = new HashSet<String>();
        
        char[] map = new char[26];
        int len = s.length();
        
        // Hashing function. We have only 4 letters which we can represent by 2 bits.
        map['A' - 'A'] = 0; // A = 00
        map['C' - 'A'] = 1; // B = 01
        map['G' - 'A'] = 2; // C = 10
        map['T' - 'A'] = 3; // D = 11
        
        for(int i=0; i<= len - 10; i++)
        {
            int sequence = 0;
            for(int j=i; j< i+10; j++)
            {
                // Shift existing sequence by two to make space for the new character coming
                sequence = sequence << 2;
                
                // Copy the character from the map 
                // and paste those two bits in the newly created space. Read bit wise OR.
                sequence = sequence | map[s.charAt(j) - 'A'];
            }
            
            
            if( set.contains(sequence) )
                res.add(s.substring(i, i+10));
            else
                set.add(sequence);
        }
        
        return new ArrayList(res);
    }
}