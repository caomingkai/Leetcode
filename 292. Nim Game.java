
// version 1: bit manipulation
// 1 - (n&3)==0, n is surely divided by 4
// 2 - (n&7)==0, n is surely divided by 8
// 3 - (n&15)==0, n is surely divided by 16

public class Solution {
    public boolean canWinNim(int n) {
        return (n&3)!=0;
    }
}


/*
// version 2:
// the one who start with a num can be divided by 4, will surely lose the game.
// since the other could empty the heap with 1/2/3 stones a time
public class Solution {
    public boolean canWinNim(int n) {
        return n%4 == 0 ? false : true;
    }
}

*/